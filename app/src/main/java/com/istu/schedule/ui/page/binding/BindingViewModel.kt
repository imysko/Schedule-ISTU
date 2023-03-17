package com.istu.schedule.ui.page.binding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.enums.UserBindingStatus
import com.istu.schedule.data.enums.UserStatus
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
import javax.inject.Inject

@HiltViewModel
class BindingViewModel @Inject constructor(
    private val useCase: GetInstitutesListUseCase
) : BaseViewModel() {

    private val _institutesList = MutableLiveData<List<Institute>>()
    val institutesList: LiveData<List<Institute>> = _institutesList

    private val _coursesList = MutableLiveData<List<Course>>()
    val coursesList: LiveData<List<Course>> = _coursesList

    private val _groupsList = MutableLiveData<List<Group>>()
    val groupsList: LiveData<List<Group>> = _groupsList

    private val _selectedInstitute = MutableLiveData<Institute>()
    val selectedInstitute: LiveData<Institute> = _selectedInstitute

    private val _selectedCourse = MutableLiveData<Course>()
    val selectedCourse: LiveData<Course> = _selectedCourse

    private val _selectedGroup = MutableLiveData<Group>()
    val selectedGroup: LiveData<Group> = _selectedGroup

    private val _selectedTeacher = MutableLiveData<Teacher>()
    val selectedTeacher: LiveData<Teacher> = _selectedTeacher

    private val _bindingUiState = MutableStateFlow(BindingUiState())
    val bindingUiState: StateFlow<BindingUiState> = _bindingUiState.asStateFlow()

    private val _userState = MutableStateFlow(UserStatus.UNKNOWN)
    val userState: StateFlow<UserStatus> = _userState.asStateFlow()

    private val _bindingState = MutableStateFlow(UserBindingStatus.UNBINDING)
    val bindingState: StateFlow<UserBindingStatus> = _bindingState.asStateFlow()

    fun getInstitutesList() {
        call({
            useCase.getInstitutesList()
        }, onSuccess = {
            _institutesList.postValue(it)
        })
    }

    private fun getCoursesList() {
        _coursesList.postValue(_institutesList.value!!.find { i -> i == _selectedInstitute.value }?.courses!!)
    }

    private fun getGroupsList() {
        _groupsList.postValue(_coursesList.value!!.find { i -> i == _selectedCourse.value }?.groups!!)
    }

    fun chooseInstitute(institute: Institute) {
        _selectedInstitute.value = institute
        getCoursesList()
    }

    fun chooseCourse(course: Course) {
        _selectedCourse.value = course
        getGroupsList()
    }
}

data class BindingUiState(
    val canChooseUserStatus: Boolean = true,
    val isShowInstitutesInput: Boolean = false,
    val canChooseInstitute: Boolean = false,
    val isShowCoursesInput: Boolean = false,
    val canChooseCourse: Boolean = false,
    val isShowGroupsInput: Boolean = false,
    val canChooseGroup: Boolean = false,
    val isShowTeachersInput: Boolean = false,
    val canEditTeacherName: Boolean = false
)