package com.istu.schedule.ui.page.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.enums.ProjfairAuthStatus
import com.istu.schedule.data.model.User
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import com.istu.schedule.util.addNewItemAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.progneo.projfair.domain.model.Candidate
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.usecase.DeleteParticipationUseCase
import me.progneo.projfair.domain.usecase.GetActiveProjectUseCase
import me.progneo.projfair.domain.usecase.GetArchiveProjectsListUseCase
import me.progneo.projfair.domain.usecase.GetCandidateUseCase
import me.progneo.projfair.domain.usecase.GetParticipationListUseCase
import me.progneo.projfair.domain.usecase.GetProjectUseCase

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val _candidateUseCase: GetCandidateUseCase,
    private val _archiveProjectListUseCase: GetArchiveProjectsListUseCase,
    private val _activeProjectUseCase: GetActiveProjectUseCase,
    private val _deleteParticipationUseCase: DeleteParticipationUseCase,
    private val _participationListUseCase: GetParticipationListUseCase,
    private val _projectUseCase: GetProjectUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _candidate = MutableLiveData<Candidate>()
    val candidate: LiveData<Candidate> = _candidate

    private val _projectsList = MutableLiveData<List<Project>>()
    val projectsList: LiveData<List<Project>> = _projectsList

    private val _participationList = MutableLiveData<List<Participation>>()
    val participationList: LiveData<List<Participation>> = _participationList

    private val _isParticipationLoaded = MutableLiveData(false)
    val isParticipationLoaded: LiveData<Boolean> = _isParticipationLoaded

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
            fetchParticipationList(token)
        }
    }

    private fun fetchCandidate(token: String) {
        call(
            apiCall = { _candidateUseCase(token) },
            onSuccess = { candidate ->
                _candidate.postValue(candidate)
            }
        )
    }

    private fun fetchActiveProject(token: String) {
        call(
            apiCall = { _activeProjectUseCase(token) },
            onSuccess = { project ->
                _projectsList.addNewItem(project)
            }
        )
    }

    private fun fetchArchiveProjects(token: String) {
        call(
            apiCall = { _archiveProjectListUseCase(token) },
            onSuccess = { list ->
                for (item in list) {
                    _projectsList.addNewItem(item)
                }
                _isProjectsLoaded.postValue(true)
            }
        )
    }

    private fun fetchParticipationList(token: String) {
        call(
            apiCall = { _participationListUseCase(token) },
            onSuccess = {
                filterParticipationList(it)
            }
        )
    }

    private fun filterParticipationList(list: List<Participation>) = viewModelScope.launch {
        for (item in list.sortedBy { participation -> participation.priority }) {
            if (item.state.id in 1..2) {
                call(
                    apiCall = { _projectUseCase(item.projectId) },
                    onSuccess = { project ->
                        _participationList.addNewItemAsync(
                            item.copy(project = project)
                        )
                    }
                )
                delay(500)
            }
        }
        _isParticipationLoaded.postValue(true)
    }

    fun deleteParticipation(participationId: Int) {
        _user.projfairToken?.let { token ->
            _isParticipationLoaded.postValue(false)
            call(
                apiCall = { _deleteParticipationUseCase(token, participationId) },
                onSuccess = {
                    _participationList.postValue(emptyList())
                    fetchParticipationList(token)
                }
            )
        }
    }
}
