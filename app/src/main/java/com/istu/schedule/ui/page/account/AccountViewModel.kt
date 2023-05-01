package com.istu.schedule.ui.page.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val _user: User
) : BaseViewModel() {
    private val _candidate = MutableLiveData<Candidate>(_user.candidate.value)
    val candidate: LiveData<Candidate> = _candidate

    fun collectSettingsState() {
        viewModelScope.launch {
            _user.candidate.collect {
                _candidate.value = it
            }
        }
    }

    fun logout() {
        _user.logoutProjfair()
    }
}
