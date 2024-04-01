package com.istu.schedule.ui.page.projfair.participation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.User
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.PriorityRequest
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.usecase.CreateParticipationUseCase
import me.progneo.projfair.domain.usecase.GetParticipationListUseCase
import me.progneo.projfair.domain.usecase.GetProjectUseCase

@HiltViewModel
class CreateParticipationViewModel @Inject constructor(
    private val _getProjectUseCase: GetProjectUseCase,
    private val _createParticipationUseCase: CreateParticipationUseCase,
    private val _participationListUseCase: GetParticipationListUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project> = _project

    private val _selectedPriorityId = MutableLiveData(1)
    val selectedPriorityId: LiveData<Int> = _selectedPriorityId

    private val _participationList = MutableLiveData<List<Participation>>()
    val participationList: LiveData<List<Participation>> = _participationList

    private val _isLoaded = MutableLiveData(false)
    val isLoaded: LiveData<Boolean> = _isLoaded

    fun fetchProject(projectId: Int) {
        call(
            handleLoading = false,
            apiCall = { _getProjectUseCase(projectId) },
            onSuccess = {
                _project.value = it
            }
        )
    }

    fun fetchParticipationList() {
        _user.projfairToken?.let { token ->
            call(
                apiCall = { _participationListUseCase(token) },
                onSuccess = {
                    for (participation in it) {
                        if (participation.state.id in 1..2) {
                            _participationList.addNewItem(participation)
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
                    call(
                        apiCall = {
                            _createParticipationUseCase(
                                token,
                                projectId!!,
                                PriorityRequest(priority)
                            )
                        },
                        onSuccess = {
                        },
                        onError = {
                        }
                    )
                }
            }
        }
    }
}
