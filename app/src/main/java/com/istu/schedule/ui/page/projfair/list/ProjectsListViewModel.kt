package com.istu.schedule.ui.page.projfair.list

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.usecase.projfair.GetProjectsListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val _projectsUseCase: GetProjectsListUseCase,
    private val _user: User,
) : BaseViewModel() {

    private val _projectsListUiState = MutableStateFlow(ProjectsListUiState())
    val projectsListUiState: StateFlow<ProjectsListUiState> = _projectsListUiState.asStateFlow()

    private val _projectsList = MutableLiveData<MutableList<Project>>()
    val projectsList: LiveData<MutableList<Project>> = _projectsList

    private var _currentPage = 1

    fun getProjectsList() {
        call({
            _user.projfairToken?.let {
                _projectsUseCase.getProjectsList(token = it, page = _currentPage)
            } ?: run {
                _projectsUseCase.getProjectsList(page = _currentPage)
            }
        }, onSuccess = {
            for (item in it) {
                _projectsList.addNewItem(item)
            }
            _currentPage += 1
        })
    }
}

data class ProjectsListUiState(
    val listState: LazyListState = LazyListState()
)