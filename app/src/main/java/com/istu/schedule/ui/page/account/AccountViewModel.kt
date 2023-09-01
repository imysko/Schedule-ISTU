package com.istu.schedule.ui.page.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.enums.ProjfairAuthStatus
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.usecase.projfair.DeleteParticipationUseCase
import com.istu.schedule.domain.usecase.projfair.GetActiveProjectUseCase
import com.istu.schedule.domain.usecase.projfair.GetArchiveProjectsListUseCase
import com.istu.schedule.domain.usecase.projfair.GetParticipationsListUseCase
import com.istu.schedule.domain.usecase.projfair.GetProjectUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import com.istu.schedule.util.addNewItemAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val _archiveProjectsListUseCase: GetArchiveProjectsListUseCase,
    private val _activeProjectUseCase: GetActiveProjectUseCase,
    private val _deleteParticipationUseCase: DeleteParticipationUseCase,
    private val _participationsListUseCase: GetParticipationsListUseCase,
    private val _projectUseCase: GetProjectUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _projectsList = MutableLiveData<List<Project>>()
    val projectsList: LiveData<List<Project>> = _projectsList

    private val _participationsList = MutableLiveData<List<Participation>>()
    val participationsList: LiveData<List<Participation>> = _participationsList

    private val _isParticipationsLoaded = MutableLiveData(false)
    val isParticipationsLoaded: LiveData<Boolean> = _isParticipationsLoaded

    private val _isProjectsLoaded = MutableLiveData(false)
    val isProjectsLoaded: LiveData<Boolean> = _isProjectsLoaded

    val authState: LiveData<ProjfairAuthStatus> = _user.authStatus
    val candidate: LiveData<Candidate?> = _user.candidate

    fun logout() {
        _user.logoutProjfair()
    }

    fun fetchCandidateInfo() {
        if (_projectsList.value?.isNotEmpty() == true) return

        _user.projfairToken?.let { token ->
            call({
                _activeProjectUseCase.getActiveProject(token)
            }, onSuccess = {
                _projectsList.addNewItem(it)
            })
            call({
                _archiveProjectsListUseCase.getArchiveProjectsList(token)
            }, onSuccess = {
                for (item in it) {
                    _projectsList.addNewItem(item)
                }
                _isProjectsLoaded.postValue(true)
            })
        }
    }

    fun fetchParticipationsList() {
        _user.projfairToken?.let { token ->
            call({
                _participationsListUseCase.getParticipationsList(token)
            }, onSuccess = {
                viewModelScope.launch {
                    for (item in it.sortedBy { participation -> participation.priority }) {
                        if (item.state.id in 1..2) {
                            _projectUseCase.getProject(item.projectId)
                                .onSuccess { project ->
                                    _participationsList.addNewItemAsync(
                                        item.copy(project = project)
                                    )
                                }
                            delay(500)
                        }
                    }
                    _isParticipationsLoaded.postValue(true)
                }
            })
        }
    }

    fun deleteParticipation(participationId: Int) {
        _user.projfairToken?.let { token ->
            _isParticipationsLoaded.postValue(false)
            call({
                _deleteParticipationUseCase.deleteParticipation(token, participationId)
            }, onSuccess = {
                _participationsList.postValue(emptyList())
                fetchParticipationsList()
            })
        }
    }
}
