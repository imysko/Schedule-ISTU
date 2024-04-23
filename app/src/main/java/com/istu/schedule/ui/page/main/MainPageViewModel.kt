package com.istu.schedule.ui.page.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.service.notification.NotificationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val notificationManager: NotificationManager
) : ViewModel() {

    // private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Clear)
    // val uiState = _uiState.asStateFlow()
//
    // fun collectNotificationList() {
    //     viewModelScope.launch {
    //         notificationManager.getNotificationList().collect {
    //             if (it.isNotEmpty()) {
    //                 _uiState.tryEmit(MainUiState.HasNotification)
    //             } else {
    //                 _uiState.tryEmit(MainUiState.Clear)
    //             }
    //         }
    //     }
    // }

    val state: StateFlow<MainUiState> = flow {
        notificationManager.getNotificationList().collect {
            if (it.isNotEmpty()) {
                emit(MainUiState.HasNotification)
            } else {
                emit(MainUiState.Clear)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MainUiState.Clear)
}
