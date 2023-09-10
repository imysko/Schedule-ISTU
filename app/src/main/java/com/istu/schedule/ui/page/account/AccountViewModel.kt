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
import com.istu.schedule.domain.usecase.projfair.GetCandidateUseCase
import com.istu.schedule.domain.usecase.projfair.GetParticipationsListUseCase
import com.istu.schedule.domain.usecase.projfair.GetProjectUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import com.istu.schedule.util.addNewItemAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val _candidateUseCase: GetCandidateUseCase,
    private val _archiveProjectsListUseCase: GetArchiveProjectsListUseCase,
    private val _activeProjectUseCase: GetActiveProjectUseCase,
    private val _deleteParticipationUseCase: DeleteParticipationUseCase,
    private val _participationsListUseCase: GetParticipationsListUseCase,
    private val _projectUseCase: GetProjectUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _candidate = MutableLiveData<Candidate>()
    val candidate: LiveData<Candidate> = _candidate

    private val _projectsList = MutableLiveData<List<Project>>()
    val projectsList: LiveData<List<Project>> = _projectsList

    private val _participationsList = MutableLiveData<List<Participation>>()
    val participationsList: LiveData<List<Participation>> = _participationsList

    private val _isParticipationsLoaded = MutableLiveData(false)
    val isParticipationsLoaded: LiveData<Boolean> = _isParticipationsLoaded

    private val _isProjectsLoaded = MutableLiveData(false)
    val isProjectsLoaded: LiveData<Boolean> = _isProjectsLoaded

    val authState: LiveData<ProjfairAuthStatus> = _user.authStatus

    fun logout() {
        _user.logoutProjfair()
    }

    fun fetchUserInfo() {
        _user.projfairToken?.let { token ->
            fetchCandidate(token)
            fetchActiveProject(token)
            fetchArchiveProjects(token)
            fetchParticipationsList(token)
        }
    }

    private fun fetchCandidate(token: String) {
        call({
            _candidateUseCase.getCandidate(token)
        }, onSuccess = { candidate ->
            _candidate.postValue(candidate)
        })
    }

    private fun fetchActiveProject(token: String) {
        call({
            _activeProjectUseCase.getActiveProject(token)
        }, onSuccess = { project ->
            _projectsList.addNewItem(project)
        })
    }

    private fun fetchArchiveProjects(token: String) {
        call({
            _archiveProjectsListUseCase.getArchiveProjectsList(token)
        }, onSuccess = { list ->
            for (item in list) {
                _projectsList.addNewItem(item)
            }
            _isProjectsLoaded.postValue(true)
        })
    }

    private fun fetchParticipationsList(token: String) {
        call({
            _participationsListUseCase.getParticipationsList(token)
        }, onSuccess = {
            filterParticipationList(it)
        })
    }

    private fun filterParticipationList(list: List<Participation>) = viewModelScope.launch {
        for (item in list.sortedBy { participation -> participation.priority }) {
            if (item.state.id in 1..2) {
                call({
                    _projectUseCase.getProject(item.projectId)
                }, onSuccess = { project ->
                    _participationsList.addNewItemAsync(
                        item.copy(project = project)
                    )
                })
                delay(500)
            }
        }
        _isParticipationsLoaded.postValue(true)
    }

    fun deleteParticipation(participationId: Int) {
        _user.projfairToken?.let { token ->
            _isParticipationsLoaded.postValue(false)
            call({
                _deleteParticipationUseCase.deleteParticipation(token, participationId)
            }, onSuccess = {
                _participationsList.postValue(emptyList())
                fetchParticipationsList(token)
            })
        }
    }
}
