package com.istu.schedule.ui.page.projfair.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.entities.projfair.Participation
import com.istu.schedule.domain.entities.projfair.Project
import com.istu.schedule.domain.usecase.projfair.GetParticipationsListUseCase
import com.istu.schedule.domain.usecase.projfair.GetProjectUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val _projectUseCase: GetProjectUseCase,
    private val _participationsUseCase: GetParticipationsListUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project> = _project

    private val _participationsList = MutableLiveData<List<Participation>>()
    val participationsList: LiveData<List<Participation>> = _participationsList

    fun fetchProjectById(projectId: Int) {
        call(
            apiCall = { _projectUseCase.getProject(projectId) },
            onSuccess = { _project.value = it }
        )
    }

    fun fetchParticipationList() {
        _user.projfairToken?.let { token ->
            call(
                apiCall = { _participationsUseCase.getParticipationsList(token) },
                onSuccess = { participations ->
                    _participationsList.postValue(
                        participations.filter {
                            it.state.id in 1..2
                        }
                    )
                }
            )
        }
    }
}
