package com.istu.schedule.ui.page.projfair.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.User
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.usecase.GetParticipationListUseCase
import me.progneo.projfair.domain.usecase.GetProjectUseCase

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val _getProjectUseCase: GetProjectUseCase,
    private val _getParticipationListUseCase: GetParticipationListUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project> = _project

    private val _participationList = MutableLiveData<List<Participation>>()
    val participationList: LiveData<List<Participation>> = _participationList

    fun fetchProjectById(projectId: Int) {
        call(
            apiCall = { _getProjectUseCase(projectId) },
            onSuccess = { _project.value = it }
        )
    }

    fun fetchParticipationList() {
        _user.projfairToken?.let { token ->
            call(
                apiCall = { _getParticipationListUseCase(token) },
                onSuccess = { participationList ->
                    _participationList.postValue(
                        participationList.filter {
                            it.state.id in 1..2
                        }
                    )
                }
            )
        }
    }
}
