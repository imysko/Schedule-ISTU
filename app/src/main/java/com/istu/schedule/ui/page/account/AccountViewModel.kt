package com.istu.schedule.ui.page.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.enums.ProjfairAuthStatus
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.usecase.projfair.DeleteParticipationUseCase
import com.istu.schedule.domain.usecase.projfair.GetActiveProjectUseCase
import com.istu.schedule.domain.usecase.projfair.GetArchiveProjectsListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val _archiveProjectsListUseCase: GetArchiveProjectsListUseCase,
    private val _activeProjectUseCase: GetActiveProjectUseCase,
    private val _deleteParticipationUseCase: DeleteParticipationUseCase,
    private val _user: User
) : BaseViewModel() {

    val participationsList: LiveData<MutableList<Participation>> = _user.participationsList

    private val _projectsList = MutableLiveData<MutableList<Project>>()
    val projectsList: LiveData<MutableList<Project>> = _projectsList

    private val _candidate = MutableLiveData<Candidate>(_user.candidate.value)
    val candidate: LiveData<Candidate> = _candidate

    val authState: LiveData<ProjfairAuthStatus> = _user.authStatus

    val canCreateParticipation: Boolean =
        _user.candidate.value?.canSendParticipations == 1 &&
            ((_user.participationsList.value?.size ?: 3) < 3)

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

    fun getCandidateInfo() {
        if (_projectsList.value?.isNotEmpty() == true) return

        _user.projfairToken?.let { token ->
            call({
                _activeProjectUseCase.getActiveProject(token)
            }, onSuccess = {
                _projectsList.addNewItem(it)
            })
            call({
                _archiveProjectsListUseCase.getArchiveProjectsList(token)
            }, onSuccess = {
                for (item in it) {
                    _projectsList.addNewItem(item)
                }
            })
        }
    }

    fun deleteParticipation(participationId: Int) {
        _user.projfairToken?.let { token ->
            call({
                _deleteParticipationUseCase.deleteParticipation(token, participationId)
            }, onSuccess = {
            })
        }
    }
}
