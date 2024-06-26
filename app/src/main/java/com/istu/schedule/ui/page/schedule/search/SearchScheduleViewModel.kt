package com.istu.schedule.ui.page.schedule.search

import com.istu.schedule.data.api.entities.RequestException
import com.istu.schedule.domain.entities.schedule.Classroom
import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.entities.schedule.Teacher
import com.istu.schedule.domain.usecase.schedule.GetClassroomsListUseCase
import com.istu.schedule.domain.usecase.schedule.GetGroupsListUseCase
import com.istu.schedule.domain.usecase.schedule.GetTeachersListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class SearchScheduleViewModel @Inject constructor(
    private val _useCaseGroupsList: GetGroupsListUseCase,
    private val _useCaseTeachersList: GetTeachersListUseCase,
    private val _useCaseClassroomsList: GetClassroomsListUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<SearchScheduleUiState>(
        SearchScheduleUiState.SearchResult
    )
    val uiState: StateFlow<SearchScheduleUiState>
        get() = _uiState.asStateFlow()

    private val _searchedLists = MutableStateFlow(SearchedLists()).apply {
        getGroups()
        getTeachers()
        getClassrooms()
    }

    private val _searchedListsHints = MutableStateFlow(SearchedLists())
    internal val searchedListsHints: StateFlow<SearchedLists> = _searchedListsHints.asStateFlow()

    private fun getGroups() {
        call({
            _useCaseGroupsList()
        }, onSuccess = { list ->
            _searchedLists.update {
                it.copy(groupsList = list)
            }
        }, onError = {
            it as RequestException
        }, onNetworkUnavailable = {
            _uiState.tryEmit(SearchScheduleUiState.NoInternetConnection)
        })
    }

    private fun getTeachers() {
        call({
            _useCaseTeachersList()
        }, onSuccess = { list ->
            _searchedLists.update {
                it.copy(teachersList = list)
            }
        }, onError = {
            it as RequestException
        }, onNetworkUnavailable = {
            _uiState.tryEmit(SearchScheduleUiState.NoInternetConnection)
        })
    }

    private fun getClassrooms() {
        call({
            _useCaseClassroomsList()
        }, onSuccess = { list ->
            _searchedLists.update {
                it.copy(classroomsList = list)
            }
        }, onError = {
            it as RequestException
        }, onNetworkUnavailable = {
            _uiState.tryEmit(SearchScheduleUiState.NoInternetConnection)
        })
    }

    fun onValueInput(value: String) {
        if (value.isNotEmpty()) {
            _uiState.tryEmit(SearchScheduleUiState.SearchResult)
        } else {
            _uiState.tryEmit(SearchScheduleUiState.EmptyBlank)
        }

        _searchedListsHints.update {
            it.copy(
                groupsList = _searchedLists.value.groupsList.filter { group ->
                    group.name!!.lowercase(Locale.getDefault())
                        .contains(value.lowercase(Locale.getDefault()))
                },
                teachersList = _searchedLists.value.teachersList.filter { teacher ->
                    teacher.fullName.lowercase(Locale.getDefault())
                        .contains(value.lowercase(Locale.getDefault()))
                },
                classroomsList = _searchedLists.value.classroomsList.filter { classroom ->
                    classroom.name.lowercase(Locale.getDefault())
                        .contains(value.lowercase(Locale.getDefault()))
                }
            )
        }
    }
}

data class SearchedLists(
    val groupsList: List<Group> = emptyList(),
    val teachersList: List<Teacher> = emptyList(),
    val classroomsList: List<Classroom> = emptyList()
)
