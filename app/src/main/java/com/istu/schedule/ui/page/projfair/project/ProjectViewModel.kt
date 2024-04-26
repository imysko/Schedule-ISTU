package com.istu.schedule.ui.page.projfair.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.usecase.GetParticipationListUseCase
import me.progneo.projfair.domain.usecase.GetProjectUseCase

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val getProjectUseCase: GetProjectUseCase,
    private val getParticipationListUseCase: GetParticipationListUseCase
) : BaseViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project> = _project

    private val _participationList = MutableLiveData<List<Participation>>()
    val participationList: LiveData<List<Participation>> = _participationList

    fun fetchProjectById(projectId: Int) {
        call(
            apiCall = { getProjectUseCase(projectId) },
            onSuccess = { _project.value = it }
        )
    }

    fun fetchParticipationList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                call(
                    apiCall = { getParticipationListUseCase() },
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
}
