package com.istu.schedule.ui.page.projfair.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import com.istu.schedule.util.addNewItemAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.progneo.projfair.data.preference.ProjfairAccessTokenManager
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
    private val getCandidateUseCase: GetCandidateUseCase,
    private val getArchiveProjectListUseCase: GetArchiveProjectsListUseCase,
    private val getActiveProjectUseCase: GetActiveProjectUseCase,
    private val deleteParticipationUseCase: DeleteParticipationUseCase,
    private val getParticipationListUseCase: GetParticipationListUseCase,
    private val getProjectUseCase: GetProjectUseCase,
    private val projfairAccessTokenManager: ProjfairAccessTokenManager
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

    val isAuthorized: StateFlow<Boolean> = flow {
        val token = projfairAccessTokenManager.get()
        emit(token != null)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)

    fun fetchUserInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchCandidate()
                fetchActiveProject()
                fetchArchiveProjects()
                fetchParticipationList()
            }
        }
    }

    private fun fetchCandidate() {
        call(
            apiCall = { getCandidateUseCase() },
            onSuccess = { candidate ->
                _candidate.postValue(candidate)
            }
        )
    }

    private fun fetchActiveProject() {
        call(
            apiCall = { getActiveProjectUseCase() },
            onSuccess = { project ->
                _projectsList.addNewItem(project)
            }
        )
    }

    private fun fetchArchiveProjects() {
        call(
            apiCall = { getArchiveProjectListUseCase() },
            onSuccess = { list ->
                for (item in list) {
                    _projectsList.addNewItem(item)
                }
                _isProjectsLoaded.postValue(true)
            }
        )
    }

    private fun fetchParticipationList() {
        call(
            apiCall = { getParticipationListUseCase() },
            onSuccess = { participationList ->
                filterParticipationList(participationList)
            }
        )
    }

    private fun filterParticipationList(list: List<Participation>) {
        viewModelScope.launch {
            for (item in list) {
                call(
                    apiCall = { getProjectUseCase(item.projectId) },
                    onSuccess = { project ->
                        _participationList.addNewItemAsync(
                            item.copy(project = project)
                        )
                    }
                )
                delay(500)
            }
            _isParticipationLoaded.postValue(true)
        }
    }

    fun deleteParticipation(participationId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _isParticipationLoaded.postValue(false)
                call(
                    apiCall = { deleteParticipationUseCase(participationId) },
                    onSuccess = {
                        _participationList.postValue(emptyList())
                        fetchParticipationList()
                    }
                )
            }
        }
    }
}
