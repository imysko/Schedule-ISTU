package com.istu.schedule.ui.page.projfair.participation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.User
import com.istu.schedule.data.api.entities.projfair.request.PriorityRequest
import com.istu.schedule.domain.entities.projfair.Participation
import com.istu.schedule.domain.entities.projfair.Project
import com.istu.schedule.domain.usecase.projfair.CreateParticipationUseCase
import com.istu.schedule.domain.usecase.projfair.GetParticipationsListUseCase
import com.istu.schedule.domain.usecase.projfair.GetProjectUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateParticipationViewModel @Inject constructor(
    private val _getProjectUseCase: GetProjectUseCase,
    private val _createParticipationUseCase: CreateParticipationUseCase,
    private val _participationsListUseCase: GetParticipationsListUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project> = _project

    private val _selectedPriorityId = MutableLiveData(1)
    val selectedPriorityId: LiveData<Int> = _selectedPriorityId

    private val _participationsList = MutableLiveData<List<Participation>>()
    val participationsList: LiveData<List<Participation>> = _participationsList

    private val _isLoaded = MutableLiveData(false)
    val isLoaded: LiveData<Boolean> = _isLoaded

    fun fetchProject(projectId: Int) {
        call(
            handleLoading = false,
            apiCall = {
                _getProjectUseCase.getProject(projectId)
            },
            onSuccess = {
                _project.value = it
            }
        )
    }

    fun fetchParticipationsList() {
        _user.projfairToken?.let { token ->
            call(
                { _participationsListUseCase.getParticipationsList(token) },
                onSuccess = {
                    for (participation in it) {
                        if (participation.state.id in 1..2) {
                            _participationsList.addNewItem(participation)
                        }
                    }
                    _isLoaded.postValue(true)
                },
                onError = {}
            )
        }
    }

    fun setPriorityId(priorityId: Int) {
        _selectedPriorityId.value = priorityId
    }

    fun createParticipation() {
        _user.projfairToken?.let { token ->
            _project.value?.id.let { projectId ->
                _selectedPriorityId.value?.let { priority ->
                    call({
                        _createParticipationUseCase.createParticipation(
                            token,
                            projectId!!,
                            PriorityRequest(priority)
                        )
                    }, onSuccess = {
                    }, onError = {
                    })
                }
            }
        }
    }
}
