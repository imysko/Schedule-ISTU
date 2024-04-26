package com.istu.schedule.ui.page.services

import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.service.notification.Notification
import com.istu.schedule.data.service.notification.NotificationManager
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.progneo.campus.data.preference.CampusAuthStateManager
import me.progneo.campus.domain.usecase.GetCountersUseCase
import me.progneo.projfair.data.preference.ProjfairAccessTokenManager
import me.progneo.projfair.domain.usecase.GetCandidateUseCase

@HiltViewModel
class ServicesViewModel @Inject constructor(
    private val campusAuthStateManager: CampusAuthStateManager,
    private val projfairAccessTokenManager: ProjfairAccessTokenManager,
    private val getCountersUseCase: GetCountersUseCase,
    private val getCandidateUseCase: GetCandidateUseCase,
    private val notificationManager: NotificationManager
) : BaseViewModel() {

    val notificationList: StateFlow<List<Notification>> = flow {
        notificationManager.getNotificationList().collect {
            emit(it)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())

    private val _campusUiState = MutableStateFlow<ServicesUiState>(ServicesUiState.Loading)
    val campusUiState = _campusUiState.asStateFlow()

    private val _projfairUiState = MutableStateFlow<ServicesUiState>(ServicesUiState.Loading)
    val projfairUiState = _projfairUiState.asStateFlow()

    fun getCampusInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val authState = campusAuthStateManager.get()
                if (authState) {
                    call(
                        apiCall = { getCountersUseCase() },
                        onSuccess = {
                            _campusUiState.tryEmit(ServicesUiState.Content(it.all.toString()))
                        },
                        onError = {
                            if (unauthorized.value == true) {
                                _campusUiState.tryEmit(ServicesUiState.Unauthorized)
                            } else {
                                _campusUiState.tryEmit(ServicesUiState.Error)
                            }
                        },
                        onNetworkUnavailable = {
                            _campusUiState.tryEmit(ServicesUiState.NetworkUnavailable)
                        }
                    )
                } else {
                    _campusUiState.tryEmit(ServicesUiState.Unauthorized)
                }
            }
        }
    }

    fun getProjfairInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val token = projfairAccessTokenManager.get()
                if (token != null) {
                    call(
                        apiCall = { getCandidateUseCase() },
                        onSuccess = {
                            _projfairUiState.tryEmit(ServicesUiState.Content(it.fio))
                        },
                        onError = {
                            if (unauthorized.value == true) {
                                _projfairUiState.tryEmit(ServicesUiState.Unauthorized)
                            } else {
                                _projfairUiState.tryEmit(ServicesUiState.Error)
                            }
                        },
                        onNetworkUnavailable = {
                            _projfairUiState.tryEmit(ServicesUiState.NetworkUnavailable)
                        }
                    )
                } else {
                    _projfairUiState.tryEmit(ServicesUiState.Unauthorized)
                }
            }
        }
    }
}
