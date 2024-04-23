package com.istu.schedule.ui.page.projfair.participation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.PriorityRequest
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.usecase.CreateParticipationUseCase
import me.progneo.projfair.domain.usecase.GetParticipationListUseCase
import me.progneo.projfair.domain.usecase.GetProjectUseCase

@HiltViewModel
class CreateParticipationViewModel @Inject constructor(
    private val getProjectUseCase: GetProjectUseCase,
    private val createParticipationUseCase: CreateParticipationUseCase,
    private val participationListUseCase: GetParticipationListUseCase
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
            apiCall = { getProjectUseCase(projectId) },
            onSuccess = {
                _project.value = it
            }
        )
    }

    fun fetchParticipationList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                call(
                    apiCall = { participationListUseCase() },
                    onSuccess = {
                        for (participation in it) {
                            if (participation.state.id in 1..2) {
                                _participationList.addNewItem(participation)
                            }
                        }
                        _isLoaded.postValue(true)
                    }
                )
            }
        }
    }

    fun setPriorityId(priorityId: Int) {
        _selectedPriorityId.value = priorityId
    }

    fun createParticipation() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _project.value?.id?.let { projectId ->
                    _selectedPriorityId.value?.let { priority ->
                        call(
                            apiCall = {
                                createParticipationUseCase(
                                    projectId,
                                    PriorityRequest(priority)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
