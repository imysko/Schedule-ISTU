package com.istu.schedule.ui.page.projfair.list

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.ProjfairFiltersState
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.projfair.Project
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
    private val _projectsUseCase: GetProjectsListUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _projectsListUiState = MutableStateFlow(ProjectsListUiState())
    val projectsListUiState: StateFlow<ProjectsListUiState> = _projectsListUiState.asStateFlow()

    private val _projectsList = MutableLiveData<List<Project>>()
    val projectsList: LiveData<List<Project>> = _projectsList

    val projfairFiltersState: StateFlow<ProjfairFiltersState> = _user.projfairFiltersState

    val canCreateParticipation: Boolean = _user.candidate.value?.canSendParticipations == 1 &&
        ((_user.participationsList.value?.size ?: 3) < 3)

    private val _isSearchCompleted = MutableLiveData(false)
    val isSearchCompleted: LiveData<Boolean> = _isSearchCompleted

    private var _currentPage = 1

    fun fetchProjectsList() {
        _isSearchCompleted.value = false
        call({
            _projectsUseCase.getProjectsList(
                token = _user.projfairToken ?: "",
                title = _projectsListUiState.value.titleSearchText,
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
                _projectsList.addNewItem(item)
            }
            _currentPage += 1
            _user.setFiltersChanged(false)
            _isSearchCompleted.postValue(true)
        }, onError = {
            _isSearchCompleted.postValue(true)
        })
    }

    fun changeSearchBarVisibility() {
        _projectsListUiState.update { it.copy(isSearchVisible = !it.isSearchVisible) }
    }

    fun inputSearchContent(content: String) {
        _projectsListUiState.update { it.copy(titleSearchText = content) }
    }

    fun clearList() {
        _currentPage = 1
        _projectsList.value = listOf()
    }
}

data class ProjectsListUiState(
    val listState: LazyListState = LazyListState(),
    val titleSearchText: String = "",
    val isSearchVisible: Boolean = false
)
