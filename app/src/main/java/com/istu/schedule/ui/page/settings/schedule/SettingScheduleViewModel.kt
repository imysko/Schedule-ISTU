package com.istu.schedule.ui.page.settings.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.entities.Subgroup
import com.istu.schedule.domain.entities.UserStatus
import com.istu.schedule.domain.entities.schedule.Course
import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.entities.schedule.Institute
import com.istu.schedule.domain.entities.schedule.Teacher
import com.istu.schedule.domain.usecase.schedule.GetInstitutesListUseCase
import com.istu.schedule.domain.usecase.schedule.GetTeachersListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SettingScheduleViewModel @Inject constructor(
    private val _useCaseInstitutesList: GetInstitutesListUseCase,
    private val _useCaseTeachersList: GetTeachersListUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<SettingScheduleUiState>(
        SettingScheduleUiState.MainScheduleSettings()
    )
    val uiState: StateFlow<SettingScheduleUiState>
        get() = _uiState.asStateFlow()

    private val _institutesList = MutableLiveData<List<Institute>>()
    val institutesList: LiveData<List<Institute>> = _institutesList

    private val _coursesList = MutableLiveData<List<Course>>()
    val coursesList: LiveData<List<Course>> = _coursesList

    private val _teachersList = MutableLiveData<List<Teacher>>()

    private val _teachersTips = MutableLiveData<List<Teacher>>()
    val teachersTips: LiveData<List<Teacher>> = _teachersTips

    private val _selectedInstitute = MutableLiveData<Institute?>()
    private val _selectedGroup = MutableLiveData<Group?>()

    private val _selectedTeacher = MutableLiveData<Teacher?>()

    private val _userState = MutableStateFlow(UserStatus.UNKNOWN)

    init {
        getInstitutesList()
        getTeachersList()

        updateMainScheduleSettingsState()
    }

    private fun updateMainScheduleSettingsState() {
        when (_user.userType) {
            UserStatus.STUDENT -> {
                _uiState.tryEmit(
                    SettingScheduleUiState.MainScheduleSettings(
                        selectedGroupDescription = _user.userDescription,
                        isSubgroupSettingAvailable = true,
                        subgroup = _user.userSubgroup
                    )
                )
            }
            UserStatus.TEACHER -> {
                _uiState.tryEmit(
                    SettingScheduleUiState.MainScheduleSettings(
                        selectedTeacherDescription = _user.userDescription,
                        isSubgroupSettingAvailable = false
                    )
                )
            }
            UserStatus.UNKNOWN -> {
                _uiState.tryEmit(SettingScheduleUiState.MainScheduleSettings())
            }
        }
    }

    private fun getInstitutesList() {
        call({
            _useCaseInstitutesList()
        }, onSuccess = {
            _institutesList.postValue(it)
        })
    }

    private fun getTeachersList() {
        call({
            _useCaseTeachersList()
        }, onSuccess = {
            _teachersList.postValue(it)
            _teachersTips.postValue(it)
        })
    }

    private fun getCoursesList() {
        _coursesList.postValue(
            _institutesList.value!!.find { i -> i == _selectedInstitute.value }?.courses!!
        )
    }

    fun onEvent(event: SettingScheduleListEvent) {
        when (event) {
            is SettingScheduleListEvent.SelectUserStatus -> selectUserStatus(event.status)
            is SettingScheduleListEvent.SelectInstitute -> selectInstitute(event.institute)
            SettingScheduleListEvent.NavigateToSubgroupSelection ->
                _uiState.tryEmit(SettingScheduleUiState.ChooseSubgroupState(_user.userSubgroup))
            is SettingScheduleListEvent.SelectGroup -> selectGroup(event.group)
            is SettingScheduleListEvent.SelectSubgroup -> selectSubgroup(event.subgroup)
            is SettingScheduleListEvent.SelectTeacher -> selectTeacher(event.teacher)
            is SettingScheduleListEvent.FilterTeacherList -> filterTeacherList(event.value)
            SettingScheduleListEvent.OnBackClickToScheduleSettings ->
                updateMainScheduleSettingsState()
            SettingScheduleListEvent.OnBackClickToChooseInstitute ->
                _uiState.tryEmit(SettingScheduleUiState.ChooseInstituteState)
        }
    }

    private fun selectUserStatus(status: UserStatus) {
        _userState.value = status

        when (_userState.value) {
            UserStatus.STUDENT -> {
                _uiState.tryEmit(SettingScheduleUiState.ChooseInstituteState)
            }
            UserStatus.TEACHER -> {
                _uiState.tryEmit(SettingScheduleUiState.ChooseTeacherState)
            }
            UserStatus.UNKNOWN -> Unit
        }
    }

    private fun selectInstitute(institute: Institute) {
        _selectedInstitute.value = institute
        getCoursesList()

        _uiState.tryEmit(
            SettingScheduleUiState.ChooseGroupState(
                instituteTitle = institute.instituteTitle ?: ""
            )
        )
    }

    private fun selectGroup(group: Group) {
        _selectedGroup.value = group

        bindUser()
        updateMainScheduleSettingsState()
    }

    private fun selectSubgroup(subgroup: Subgroup) {
        _user.userSubgroup = subgroup

        updateMainScheduleSettingsState()
    }

    private fun selectTeacher(teacher: Teacher) {
        _selectedTeacher.value = teacher

        bindUser()
        updateMainScheduleSettingsState()
    }

    private fun filterTeacherList(value: String) {
        _teachersTips.value = _teachersList.value!!.filter {
            it.fullName.lowercase(Locale.getDefault())
                .contains(value.lowercase(Locale.getDefault()))
        }
    }

    private fun bindUser() {
        _user.userType = _userState.value

        when (_userState.value) {
            UserStatus.STUDENT -> {
                _user.userId = _selectedGroup.value?.groupId
                _user.userDescription = _selectedGroup.value?.name ?: ""
            }
            UserStatus.TEACHER -> {
                _user.userId = _selectedTeacher.value?.teacherId
                _user.userDescription = _selectedTeacher.value?.fullName ?: ""
                _user.userSubgroup = Subgroup.ALL
            }
            UserStatus.UNKNOWN -> Unit
        }
    }
}
