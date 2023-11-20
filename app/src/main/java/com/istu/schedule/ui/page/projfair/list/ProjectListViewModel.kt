package com.istu.schedule.ui.page.projfair.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.api.entities.projfair.enums.ListStatus
import com.istu.schedule.data.api.entities.projfair.ProjfairFiltersState
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.entities.projfair.Candidate
import com.istu.schedule.domain.entities.projfair.Project
import com.istu.schedule.domain.usecase.projfair.GetCandidateUseCase
import com.istu.schedule.domain.usecase.projfair.GetParticipationsListUseCase
import com.istu.schedule.domain.usecase.projfair.GetProjectsListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProjectsListViewModel @Inject constructor(
    private val _candidateUseCase: GetCandidateUseCase,
    private val _projectsUseCase: GetProjectsListUseCase,
    private val _participationsListUseCase: GetParticipationsListUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _candidate = MutableLiveData<Candidate>()

    val projfairFiltersState: StateFlow<ProjfairFiltersState> = _user.projfairFiltersState

    private val _projectListUiState = MutableStateFlow(ProjectsListUiState())
    val projectListUiState: StateFlow<ProjectsListUiState> = _projectListUiState.asStateFlow()

    private val _projectList = MutableLiveData<List<Project>>()
    val projectList: LiveData<List<Project>> = _projectList

    private val _canCreateParticipation = MutableStateFlow(false)
    val canCreateParticipation: StateFlow<Boolean> = _canCreateParticipation

    private val _listStatus = MutableLiveData(ListStatus.None)
    val pageStatus: LiveData<ListStatus> = _listStatus

    private var _currentPage = 1

    fun fetchProjectList() {
        if (_listStatus.value == ListStatus.Complete || _listStatus.value == ListStatus.None) {
            if (_currentPage == 1) {
                _listStatus.postValue(ListStatus.FirstLoading)
            } else {
                _listStatus.postValue(ListStatus.Loading)
            }

            call({
                _projectsUseCase.getProjectList(
                    token = _user.projfairToken ?: "",
                    title = _projectListUiState.value.searchText,
                    page = _currentPage,
                    difficulties = _user.projfairFiltersState.value.difficultiesList,
                    states = _user.projfairFiltersState.value.statusesList,
                    specialties = _user.projfairFiltersState.value.specialitiesList.map {
                        it.first
                    },
                    skills = _user.projfairFiltersState.value.skillsList.map { it.first }
                )
            }, onSuccess = {
                for (item in it) {
                    _projectList.addNewItem(item)
                }
                _currentPage += 1
                _user.setFiltersChanged(false)
                _listStatus.postValue(ListStatus.Complete)
            }, onNetworkUnavailable = { _listStatus.postValue(ListStatus.NoNetwork) })
        }
    }

    fun fetchParticipationsList() {
        _user.projfairToken?.let { token ->
            call({
                _candidateUseCase.getCandidate(token)
            }, onSuccess = { candidate ->
                _candidate.postValue(candidate)
            }, onNetworkUnavailable = { _listStatus.postValue(ListStatus.NoNetwork) })

            call({
                _participationsListUseCase.getParticipationsList(token)
            }, onSuccess = { participations ->
                _canCreateParticipation.value =
                    participations.count { it.state.id in 1..2 } < 3 &&
                    _candidate.value?.canSendParticipations == 1
            }, onNetworkUnavailable = {
                _listStatus.postValue(ListStatus.NoNetwork)
            }, handleLoading = false)
        }
    }

    fun inputSearchContent(content: String) {
        _projectListUiState.update { it.copy(searchText = content) }
    }

    fun clearList() {
        _currentPage = 1
        _projectList.value = listOf()
    }
}

data class ProjectsListUiState(val searchText: String = "")
