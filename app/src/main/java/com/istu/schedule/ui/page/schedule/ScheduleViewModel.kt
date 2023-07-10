package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.enums.ScheduleType
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.model.User
import com.istu.schedule.data.model.Week
import com.istu.schedule.domain.model.schedule.Classroom
import com.istu.schedule.domain.model.schedule.Group
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.domain.model.schedule.Teacher
import com.istu.schedule.domain.usecase.schedule.GetClassroomsListUseCase
import com.istu.schedule.domain.usecase.schedule.GetGroupsListUseCase
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import com.istu.schedule.domain.usecase.schedule.GetTeachersListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val _useCaseScheduleOnDay: GetScheduleOnDayUseCase,
    private val _useCaseGroupsList: GetGroupsListUseCase,
    private val _useCaseTeachersList: GetTeachersListUseCase,
    private val _useCaseClassroomsList: GetClassroomsListUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _scheduleUiState = MutableStateFlow(ScheduleUiState())
    val scheduleUiState: StateFlow<ScheduleUiState> = _scheduleUiState.asStateFlow()

    private val _schedule = MutableLiveData<StudyDay>()
    val schedule: LiveData<StudyDay> = _schedule

    private val _searchedLists = MutableStateFlow(SearchedLists()).apply {
        getGroups()
        getTeachers()
        getClassrooms()
    }

    private val _searchedListsTips = MutableStateFlow(SearchedLists())
    internal val searchedListsTips: StateFlow<SearchedLists> = _searchedListsTips.asStateFlow()

    private val _searchedSchedule = MutableLiveData<StudyDay>()
    val searchedSchedule: LiveData<StudyDay> = _searchedSchedule

    val currentDateTime = flow {
        while (true) {
            delay(1.seconds)
            emit(LocalDateTime.now())
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            LocalDateTime.now()
        )

    private val _weeksList = MutableLiveData<MutableList<Week>>().apply {
        value = mutableListOf()

        val startDate = currentDateTime.value.toLocalDate()
            .minusDays((currentDateTime.value.toLocalDate().dayOfWeek.value - 1).toLong())

        value!!.add(Week(startDate.minusWeeks(1)))
        value!!.add(Week(startDate))
        value!!.add(Week(startDate.plusWeeks(1)))
    }
    val weeksList: LiveData<MutableList<Week>> = _weeksList

    private val _selectedDate = MutableLiveData<LocalDate>().apply {
        value = currentDateTime.value.toLocalDate()
    }
    val selectedDate: LiveData<LocalDate> = _selectedDate

    init {
        updateUserInformation()
    }

    fun updateUserInformation() {
        if (_user.userType != UserStatus.UNKNOWN) {
            _scheduleUiState.update {
                it.copy(
                    isUserBinded = true,
                    userDescription = _user.userDescription
                )
            }
        }
    }

    fun addWeekForward() {
        _weeksList.value!!.add(
            0,
            Week(
                startDayOfWeek = _weeksList.value!!.first().startDayOfWeek.minusWeeks(1)
            )
        )
    }

    fun addWeekBackward() {
        _weeksList.value!!.add(
            Week(
                startDayOfWeek = _weeksList.value!!.last().startDayOfWeek.plusWeeks(1)
            )
        )
    }

    fun selectDate(selectedDate: LocalDate) {
        _selectedDate.value = selectedDate
    }

    fun changeSearchBarVisibility() {
        _scheduleUiState.update {
            it.copy(
                isSearchBarVisible = !it.isSearchBarVisible,
                isCalendarVisible = !it.isCalendarVisible,
                isScheduleListVisible = !it.isScheduleListVisible,
                isSearchContentVisible = !it.isSearchContentVisible
            )
        }
    }

    fun onValueInput(value: String) {
        _scheduleUiState.update {
            it.copy(
                isFoundedListsVisible = value.isNotEmpty()
            )
        }

        _searchedListsTips.update {
            it.copy(
                groupList = _searchedLists.value.groupList.filter { group ->
                    group.name!!.lowercase(Locale.getDefault())
                        .contains(value.lowercase(Locale.getDefault()))
                },
                teacherList = _searchedLists.value.teacherList.filter { teacher ->
                    teacher.fullName.lowercase(Locale.getDefault())
                        .contains(value.lowercase(Locale.getDefault()))
                },
                classroomList = _searchedLists.value.classroomList.filter { classroom ->
                    classroom.name.lowercase(Locale.getDefault())
                        .contains(value.lowercase(Locale.getDefault()))
                }
            )
        }
    }

    fun getSchedule() {
        when (_user.userType) {
            UserStatus.STUDENT -> {
                call({
                    _useCaseScheduleOnDay.getScheduleOnDay(
                        scheduleType = ScheduleType.BY_GROUP,
                        id = _user.userId!!,
                        dateString = _selectedDate.value.toString()
                    )
                }, onSuccess = { list ->
                    _schedule.postValue(list.first { it.date == _selectedDate.value.toString() })
                }, onError = {
                    it as RequestException
                    _schedule.postValue(
                        StudyDay(
                            date = _selectedDate.value.toString(),
                            lessons = emptyList()
                        )
                    )
                })
            }

            UserStatus.TEACHER -> {
                call({
                    _useCaseScheduleOnDay.getScheduleOnDay(
                        scheduleType = ScheduleType.BY_TEACHER,
                        id = _user.userId!!,
                        dateString = _selectedDate.value.toString()
                    )
                }, onSuccess = { list ->
                    _schedule.postValue(list.first { it.date == _selectedDate.value.toString() })
                }, onError = {
                    it as RequestException
                    _schedule.postValue(
                        StudyDay(
                            date = _selectedDate.value.toString(),
                            lessons = emptyList()
                        )
                    )
                })
            }

            else -> {}
        }
    }

    private fun getGroups() {
        call({
            _useCaseGroupsList.getGroupsList()
        }, onSuccess = { list ->
            _searchedLists.update {
                it.copy(groupList = list)
            }
        }, onError = {
            it as RequestException
        })
    }

    private fun getTeachers() {
        call({
            _useCaseTeachersList.getTeachersList()
        }, onSuccess = { list ->
            _searchedLists.update {
                it.copy(teacherList = list)
            }
        }, onError = {
            it as RequestException
        })
    }

    private fun getClassrooms() {
        call({
            _useCaseClassroomsList.getClassroomsList()
        }, onSuccess = { list ->
            _searchedLists.update {
                it.copy(classroomList = list)
            }
        }, onError = {
            it as RequestException
        })
    }
}

data class ScheduleUiState(
    val isScheduleListVisible: Boolean = true,
    val isCalendarVisible: Boolean = true,
    val isSearchBarVisible: Boolean = false,
    val isSearchContentVisible: Boolean = false,
    val isFoundedListsVisible: Boolean = false,
    val isUserBinded: Boolean = false,
    val userDescription: String? = null,
    val calendarState: LazyListState = LazyListState()
)

data class SearchedLists(
    val groupList: List<Group> = emptyList(),
    val teacherList: List<Teacher> = emptyList(),
    val classroomList: List<Classroom> = emptyList()
)
