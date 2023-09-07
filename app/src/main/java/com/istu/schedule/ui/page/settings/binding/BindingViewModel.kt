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
class BindingViewModel @Inject constructor(
    private val _useCaseInstitutesList: GetInstitutesListUseCase,
    private val _useCaseTeachersList: GetTeachersListUseCase,
    private val _user: User
) : BaseViewModel() {

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

    private val _bindingUiState = MutableStateFlow(BindingUiState())
    val bindingUiState: StateFlow<BindingUiState> = _bindingUiState.asStateFlow()

    init {
        getInstitutesList()
        getTeachersList()

        _bindingUiState.update {
            when (_user.userType) {
                UserStatus.STUDENT -> {
                    it.copy(
                        selectedGroupDescription = _user.userDescription
                    )
                }

                UserStatus.TEACHER -> {
                    it.copy(
                        selectedTeacherDescription = _user.userDescription
                    )
                }

                else -> {
                    it.copy()
                }
            }
        }
    }

    private fun getInstitutesList() {
        call({
            _useCaseInstitutesList.getInstitutesList()
        }, onSuccess = {
            _institutesList.postValue(it)
        })
    }

    private fun getTeachersList() {
        call({
            _useCaseTeachersList.getTeachersList()
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

    fun selectUserStatus(status: UserStatus) {
        _userState.value = status

        _bindingUiState.update {
            when (_userState.value) {
                UserStatus.STUDENT -> {
                    it.copy(
                        isShowChooseUserStatusPage = false,
                        isShowChooseInstitutePage = true
                    )
                }

                UserStatus.TEACHER -> {
                    it.copy(
                        isShowChooseUserStatusPage = false,
                        isShowChooseTeacherPage = true
                    )
                }

                UserStatus.UNKNOWN -> {
                    it.copy()
                }
            }
        }
    }

    fun onClickBackToChooseUserState() {
        _bindingUiState.update {
            it.copy(
                isShowChooseUserStatusPage = true,
                isShowChooseInstitutePage = false,
                isShowChooseTeacherPage = false
            )
        }
    }

    fun onClickBackToChooseInstitute() {
        _bindingUiState.update {
            it.copy(
                isShowChooseInstitutePage = true,
                isShowChooseGroupPage = false
            )
        }
    }

    fun selectInstitute(institute: Institute) {
        _selectedInstitute.value = institute
        getCoursesList()

        _bindingUiState.update {
            it.copy(
                isShowChooseInstitutePage = false,
                isShowChooseGroupPage = true,
                selectedInstituteDescription = institute.instituteTitle
            )
        }
    }

    fun selectGroup(group: Group) {
        _selectedGroup.value = group

        _bindingUiState.update {
            it.copy(
                isShowChooseGroupPage = false,
                isShowChooseUserStatusPage = true,
                selectedGroupDescription = group.name,
                selectedTeacherDescription = null
            )
        }

        bindUser()
    }

    fun selectTeacher(teacher: Teacher) {
        _selectedTeacher.value = teacher

        _bindingUiState.update {
            it.copy(
                isShowChooseTeacherPage = false,
                isShowChooseUserStatusPage = true,
                selectedTeacherDescription = teacher.fullName,
                selectedGroupDescription = null
            )
        }

        bindUser()
    }

    fun onTeacherInput(value: String) {
        _teachersTips.value = _teachersList.value!!.filter {
            it.fullName.lowercase(Locale.getDefault())
                .contains(value.lowercase(Locale.getDefault()))
        }
    }

    private fun bindUser() {
        _user.userType = _userState.value

        when (_userState.value) {
            UserStatus.STUDENT -> {
                _user.userId = _selectedGroup.value!!.groupId
                _user.userDescription = _selectedGroup.value!!.name
            }

            UserStatus.TEACHER -> {
                _user.userId = _selectedTeacher.value!!.teacherId
                _user.userDescription = _selectedTeacher.value!!.fullName
            }

            UserStatus.UNKNOWN -> {}
        }
    }
}

data class BindingUiState(
    val isShowChooseUserStatusPage: Boolean = true,
    val isShowChooseInstitutePage: Boolean = false,
    val isShowChooseGroupPage: Boolean = false,
    val isShowChooseTeacherPage: Boolean = false,
    val selectedInstituteDescription: String? = null,
    val selectedGroupDescription: String? = null,
    val selectedTeacherDescription: String? = null
)
