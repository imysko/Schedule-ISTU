package com.istu.schedule.ui.page.settings.binding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.schedule.Course
import com.istu.schedule.domain.model.schedule.Group
import com.istu.schedule.domain.model.schedule.Institute
import com.istu.schedule.domain.model.schedule.Teacher
import com.istu.schedule.domain.usecase.schedule.GetInstitutesListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BindingViewModel @Inject constructor(
    private val _useCase: GetInstitutesListUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _institutesList = MutableLiveData<List<Institute>>()
    val institutesList: LiveData<List<Institute>> = _institutesList

    private val _coursesList = MutableLiveData<List<Course>>()
    val coursesList: LiveData<List<Course>> = _coursesList

    private val _groupsList = MutableLiveData<List<Group>>()
    val groupsList: LiveData<List<Group>> = _groupsList


    private val _selectedInstitute = MutableLiveData<Institute>()
    private val _selectedCourse = MutableLiveData<Course>()
    private val _selectedGroup = MutableLiveData<Group>()
    private val _selectedTeacher = MutableLiveData<Teacher>()


    private val _userState = MutableStateFlow(UserStatus.UNKNOWN)
    val userState: StateFlow<UserStatus> = _userState.asStateFlow()

    private val _bindingUiState = MutableStateFlow(BindingUiState())
    val bindingUiState: StateFlow<BindingUiState> = _bindingUiState.asStateFlow()

    init {
        getInstitutesList()
        getTeachersList()
    }

    private fun getInstitutesList() {
        call({
            _useCase.getInstitutesList()
        }, onSuccess = {
            _institutesList.postValue(it)
        })
    }

    private fun getTeachersList() {

    }

    private fun getCoursesList() {
        _coursesList.postValue(_institutesList.value!!.find { i -> i == _selectedInstitute.value }?.courses!!)
    }

    private fun getGroupsList() {
        _groupsList.postValue(_coursesList.value!!.find { i -> i == _selectedCourse.value }?.groups!!)
    }

    fun selectUserStatus(status: UserStatus) {
        _userState.value = status

        _bindingUiState.update {
            when (_userState.value) {
                UserStatus.STUDENT -> {
                    it.copy(
                        isShowInstitutesInput = true,
                    )
                }
                UserStatus.TEACHER -> {
                    it.copy(
                        isShowTeachersInput = true
                    )
                }
                UserStatus.UNKNOWN -> { it.copy()}
            }
        }
    }

    fun selectInstitute(institute: Institute) {
        _selectedInstitute.value = institute
        getCoursesList()

        _bindingUiState.update {
            it.copy(
                canChooseInstitute = false,
                selectedInstituteText = _selectedInstitute.value!!.instituteTitle!!,
                isShowCoursesInput = true,
                canChooseCourse = true
            )
        }
    }

    fun selectCourse(course: Course) {
        _selectedCourse.value = course
        getGroupsList()

        _bindingUiState.update {
            it.copy(
                canChooseCourse = false,
                selectedCourseText = _selectedCourse.value!!.courseNumber.toString(),
                isShowGroupsInput = true,
                canChooseGroup = true
            )
        }
    }

    fun selectGroup(group: Group) {
        _selectedGroup.value = group

        _bindingUiState.update {
            it.copy(
                canChooseGroup = false,
                selectedGroupText = _selectedGroup.value!!.name!!,
                canBinding = true,
                isShowFloatingButton = true
            )
        }
    }

    fun bindPressed() {
        if (_bindingUiState.value.canBinding) {
            _user.userType = _userState.value
            when (_userState.value) {
                UserStatus.STUDENT -> {
                    _user.userId = _selectedGroup.value!!.groupId
                    _user.userDescription = _selectedGroup.value!!.name
                }
                UserStatus.TEACHER -> {
                    _user.userId = _selectedTeacher.value!!.teacherId
                    _user.userDescription = _selectedTeacher.value!!.fullname
                }
                UserStatus.UNKNOWN -> { }
            }
        }
        else {
            changeDialogStatus(true)
        }
    }

    fun changeDialogStatus(value: Boolean) {
        _bindingUiState.update { it.copy(isShowIncompleteInputDialog = value) }
    }
}

data class BindingUiState(
    val isShowInstitutesInput: Boolean = false,
    val canChooseInstitute: Boolean = true,
    val selectedInstituteText: String = "",
    val isShowCoursesInput: Boolean = false,
    val canChooseCourse: Boolean = false,
    val selectedCourseText: String = "",
    val isShowGroupsInput: Boolean = false,
    val canChooseGroup: Boolean = false,
    val selectedGroupText: String = "",
    val isShowTeachersInput: Boolean = false,
    val canEditTeacherName: Boolean = true,
    val selectedTeacherText: String = "",
    val canBinding: Boolean = false,
    val isShowFloatingButton: Boolean = false,
    val isShowIncompleteInputDialog: Boolean = false
)