package com.istu.schedule.ui.page.projfair.participation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.usecase.projfair.CreateParticipationUseCase
import com.istu.schedule.domain.usecase.projfair.GetProjectUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateParticipationViewModel @Inject constructor(
    private val _getProjectUseCase: GetProjectUseCase,
    private val _createParticipationUseCase: CreateParticipationUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project> = _project

    private val _selectedPriorityId = MutableLiveData(1)
    val selectedPriorityId: LiveData<Int> = _selectedPriorityId

    fun getProject(projectId: Int) {
        call({
            _getProjectUseCase.getProject(projectId)
        }, onSuccess = {
            _project.value = it
        })
    }

    fun createParticipation() {
        _user.projfairToken?.let { token ->
            _project.value?.id.let { projectId ->
                call({
                    _createParticipationUseCase.createParticipation(token, projectId!!)
                }, onSuccess = {
                }, onError = {
                })
            }
        }
    }
}
