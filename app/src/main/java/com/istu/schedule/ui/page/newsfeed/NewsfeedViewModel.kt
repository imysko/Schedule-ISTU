package com.istu.schedule.ui.page.newsfeed

import androidx.lifecycle.viewModelScope
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.progneo.campus.data.preference.CampusAuthStateManager
import me.progneo.campus.data.preference.LastSeenBlogPostIdManager
import me.progneo.campus.domain.usecase.GetBlogPostListUseCase
import me.progneo.campus.domain.usecase.GetUserListUseCase
import me.progneo.campus.ui.mappers.toUiModel

@HiltViewModel
class NewsfeedViewModel @Inject constructor(
    private val campusAuthStateManager: CampusAuthStateManager,
    private val lastSeenBlogPostIdManager: LastSeenBlogPostIdManager,
    private val getBlogPostListUseCase: GetBlogPostListUseCase,
    private val getUserListUseCase: GetUserListUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<NewsfeedUiState>(NewsfeedUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getBlogPostList() {
        _uiState.tryEmit(NewsfeedUiState.Loading)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val isAuthorized = campusAuthStateManager.get()
                if (isAuthorized) {
                    call(
                        apiCall = { getBlogPostListUseCase() },
                        onSuccess = { list ->
                            val blogPostList = list.map { it.toUiModel() }
                            val userIdList = blogPostList.map { it.authorId }.distinct()

                            viewModelScope.launch {
                                withContext(Dispatchers.IO) {
                                    call(
                                        apiCall = {
                                            getUserListUseCase(
                                                userIdList = userIdList

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
                                            viewModelScope.launch {
                                                withContext(Dispatchers.IO) {
                                                    lastSeenBlogPostIdManager.save(
                                                        blogPostList[0].id
                                                    )
                                                }
                                            }
                                            _uiState.tryEmit(NewsfeedUiState.Content(blogPostList))
                                        },
                                        onError = {
                                            if (unauthorized.value == true) {
                                                _uiState.tryEmit(NewsfeedUiState.Unauthorized)
                                            } else {
                                                _uiState.tryEmit(NewsfeedUiState.Error)
                                            }
                                        },
                                        onNetworkUnavailable = {
                                            _uiState.tryEmit(NewsfeedUiState.NetworkUnavailable)
                                        }
                                    )
                                }
                            }
                        },
                        onError = {
                            if (unauthorized.value == true) {
                                _uiState.tryEmit(NewsfeedUiState.Unauthorized)
                            } else {
                                _uiState.tryEmit(NewsfeedUiState.Error)
                            }
                        },
                        onNetworkUnavailable = {
                            _uiState.tryEmit(NewsfeedUiState.NetworkUnavailable)
                        }
                    )
                } else {
                    _uiState.tryEmit(NewsfeedUiState.Unauthorized)
                }
            }
        }
    }
}
