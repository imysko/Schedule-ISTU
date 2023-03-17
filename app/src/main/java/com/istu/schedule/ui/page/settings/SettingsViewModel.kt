package com.istu.schedule.ui.page.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val _user: User
) : ViewModel() {

    private val _settingsUiState = MutableStateFlow(SettingsUiState())
    val settingsUiState: StateFlow<SettingsUiState> = _settingsUiState.asStateFlow()

    fun collectSettingsState() {
        viewModelScope.launch {
            _user.candidate.collect() { candidate ->
                _settingsUiState.update {
                    it.copy(
                        isProjfairAuthenticated = candidate != null,
                        projfairUsername = candidate?.fio ?: ""
                    )
                }
            }
        }
    }

    fun logoutProjfair() {
        _user.logoutProjfair()
    }
}

data class SettingsUiState(
    val isProjfairAuthenticated: Boolean = false,
    val projfairUsername: String = ""
)