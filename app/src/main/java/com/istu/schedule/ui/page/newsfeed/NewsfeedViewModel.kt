package com.istu.schedule.ui.page.newsfeed

import androidx.lifecycle.viewModelScope
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.progneo.campus.data.preference.AuthStateManager
import me.progneo.campus.data.preference.AuthTokenManager
import me.progneo.campus.data.preference.RefreshTokenManager
import me.progneo.campus.domain.usecase.GetBlogPostListUseCase
import me.progneo.campus.domain.usecase.GetUserListUseCase
import me.progneo.campus.domain.usecase.RefreshTokenUseCase
import me.progneo.campus.ui.mappers.toUiModel

@HiltViewModel
class NewsfeedViewModel @Inject constructor(
    private val authStateManager: AuthStateManager,
    private val authTokenManager: AuthTokenManager,
    private val refreshTokenManager: RefreshTokenManager,
    private val getBlogPostListUseCase: GetBlogPostListUseCase,
    private val getUserListUseCase: GetUserListUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<NewsfeedUiState>(NewsfeedUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getBlogPostList() {
        viewModelScope.launch {
            _uiState.tryEmit(NewsfeedUiState.Loading)

            withContext(Dispatchers.IO) {
                val isAuthorized = authStateManager.get()

                if (isAuthorized) {
                    refreshTokenManager.get()?.let { refreshToken ->
                        call(
                            apiCall = { refreshTokenUseCase(refreshToken = refreshToken) },
                            onSuccess = { tokenResponse ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    authTokenManager.save(tokenResponse.accessToken)
                                    refreshTokenManager.save(tokenResponse.refreshToken)
                                }
                                getBlogPostList(tokenResponse.accessToken)
                            },
                            onError = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    authStateManager.save(false)
                                }
                                _uiState.tryEmit(NewsfeedUiState.Unauthorized)
                            },
                            onNetworkUnavailable = {
                                _uiState.tryEmit(NewsfeedUiState.NetworkUnavailable)
                            }
                        )
                    }
                } else {
                    _uiState.tryEmit(NewsfeedUiState.Unauthorized)
                }
            }
        }
    }

    private fun getBlogPostList(token: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                call(
                    apiCall = { getBlogPostListUseCase(token) },
                    onSuccess = { list ->
                        val blogPostList = list.map { it.toUiModel() }
                        val userIdList = blogPostList.map { it.authorId }.distinct()

                        viewModelScope.launch {
                            withContext(Dispatchers.IO) {
                                call(
                                    apiCall = {
                                        getUserListUseCase(
                                            userIdList = userIdList,
                                            token = token
                                        )
                                    },
                                    onSuccess = { userList ->
                                        userList.forEach { user ->
                                            blogPostList.filter { blogPost ->
                                                blogPost.authorId == user.id
                                            }.forEach { blogPost ->
                                                blogPost.author = user
                                            }
                                        }

                                        _uiState.tryEmit(NewsfeedUiState.Content(blogPostList))
                                    },
                                    onError = {
                                        _uiState.tryEmit(NewsfeedUiState.Error)
                                    },
                                    onNetworkUnavailable = {
                                        _uiState.tryEmit(NewsfeedUiState.NetworkUnavailable)
                                    }
                                )
                            }
                        }
                    },
                    onError = {
                        _uiState.tryEmit(NewsfeedUiState.Error)
                    },
                    onNetworkUnavailable = {
                        _uiState.tryEmit(NewsfeedUiState.NetworkUnavailable)
                    }
                )
            }
        }
    }
}
