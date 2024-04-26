package com.istu.schedule.ui.page.projfair.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.api.entities.projfair.enums.ListStatus
import com.istu.schedule.data.model.User
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.progneo.projfair.domain.model.Candidate
import me.progneo.projfair.domain.model.FiltersState
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.usecase.GetCandidateUseCase
import me.progneo.projfair.domain.usecase.GetParticipationListUseCase
import me.progneo.projfair.domain.usecase.GetProjectListUseCase

@HiltViewModel
class ProjectsListViewModel @Inject constructor(
    private val getCandidateUseCase: GetCandidateUseCase,
    private val getProjectUseCase: GetProjectListUseCase,
    private val getParticipationListUseCase: GetParticipationListUseCase,
    private val user: User
) : BaseViewModel() {

    private val _candidate = MutableLiveData<Candidate>()

    val projfairFiltersState: StateFlow<FiltersState> = user.projfairFiltersState

    private val _projectListUiState = MutableStateFlow(ProjectListUiState())
    val projectListUiState: StateFlow<ProjectListUiState> = _projectListUiState.asStateFlow()

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

            call(
                apiCall = {
                    getProjectUseCase(
                        title = _projectListUiState.value.searchText,
                        page = _currentPage,
                        difficulties = user.projfairFiltersState.value.difficultiesList,
                        states = user.projfairFiltersState.value.statusesList,
                        specialties = user.projfairFiltersState.value.specialitiesList.map {
                            it.first
                        },
                        skills = user.projfairFiltersState.value.skillsList.map { it.first }
                    )
                },
                onSuccess = {
                    for (item in it) {
                        _projectList.addNewItem(item)
                    }
                    _currentPage += 1
                    user.setFiltersChanged(false)
                    _listStatus.postValue(ListStatus.Complete)
                },
                onNetworkUnavailable = { _listStatus.postValue(ListStatus.NoNetwork) }
            )
        }
    }

    fun fetchParticipationList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                call(
                    apiCall = { getCandidateUseCase() },
                    onSuccess = { candidate ->
                        _candidate.postValue(candidate)
                    },
                    onNetworkUnavailable = { _listStatus.postValue(ListStatus.NoNetwork) }
                )

                call(
                    apiCall = { getParticipationListUseCase() },
                    onSuccess = { participationList ->
                        _canCreateParticipation.value =
                            participationList.count { it.state.id in 1..2 } < 3 &&
                            _candidate.value?.canSendParticipations == 1
                    },
                    onNetworkUnavailable = {
                        _listStatus.postValue(ListStatus.NoNetwork)
                    },
                    handleLoading = false
                )
            }
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

data class ProjectListUiState(val searchText: String = "")
