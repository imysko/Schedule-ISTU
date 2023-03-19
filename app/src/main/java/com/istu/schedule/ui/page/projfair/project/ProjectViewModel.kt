package com.istu.schedule.ui.page.projfair.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.usecase.projfair.GetProjectUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val useCase: GetProjectUseCase
) : BaseViewModel()  {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project> = _project

    fun getProjectById(projectId: Int) {
        call({
            useCase.getProject(projectId)
        }, onSuccess = {
            _project.value = it
        })
    }
}