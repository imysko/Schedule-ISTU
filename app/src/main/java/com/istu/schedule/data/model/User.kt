package com.istu.schedule.data.model

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.enums.ProjfairAuthStatus
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.domain.usecase.projfair.GetCandidateUseCase
import com.istu.schedule.domain.usecase.projfair.GetParticipationsListUseCase
import com.istu.schedule.domain.usecase.projfair.GetProjectUseCase
import com.istu.schedule.util.addNewItemAsync
import com.istu.schedule.util.toUserStatusEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class User @Inject constructor(
    private val _sharedPreference: SharedPreferences,
    private val _candidateUseCase: GetCandidateUseCase,
    private val _projectUseCase: GetProjectUseCase,
    private val _participationsListUseCase: GetParticipationsListUseCase
) {

    private val _projfairFiltersState = MutableStateFlow(ProjfairFiltersState())
    val projfairFiltersState: StateFlow<ProjfairFiltersState> = _projfairFiltersState.asStateFlow()

    private val _candidate = MutableLiveData<Candidate?>(null)
    val candidate: LiveData<Candidate?> = _candidate

    private val _authStatus = MutableLiveData(ProjfairAuthStatus.UNDEFINED)
    val authStatus: LiveData<ProjfairAuthStatus> = _authStatus

    private val _participationsList = MutableLiveData<List<Participation>>()
    val participationsList: LiveData<List<Participation>> = _participationsList

    init {
        setAuth()
    }

    fun setProjfairFilters(projfairFiltersState: ProjfairFiltersState) {
        _projfairFiltersState.value = projfairFiltersState
    }

    fun setFiltersChanged(isUpdated: Boolean) {
        _projfairFiltersState.value = _projfairFiltersState.value.copy(isChanged = isUpdated)
    }

    private fun setAuth() {
        if (projfairToken != null) {
            _authStatus.value = ProjfairAuthStatus.SUCCESS
            fetchCandidate()
            fetchParticipationsList()
        } else {
            _authStatus.value = ProjfairAuthStatus.AUTH
        }
    }

    private fun fetchCandidate() {
        CoroutineScope(Dispatchers.IO).launch {
            _candidateUseCase.getCandidate(projfairToken!!)
                .onSuccess {
                    _candidate.postValue(it)
                }
                .onFailure {
                    _authStatus.postValue(ProjfairAuthStatus.AUTH)
                    projfairToken = null
                }
        }
    }

    fun fetchParticipationsList() {
        CoroutineScope(Dispatchers.IO).launch {
            _participationsListUseCase.getParticipationsList(projfairToken!!)
                .onSuccess {
                    for (item in it.sortedBy { participation -> participation.priority }) {
                        if (item.state.id in 1..2) {
                            _projectUseCase.getProject(item.projectId).onSuccess { project ->
                                val participation = item.copy(project = project)
                                _participationsList.addNewItemAsync(participation)
                            }
                        }
                    }
                }
                .onFailure {
                    delay(5000)
                    fetchParticipationsList()
                }
        }
    }

    fun loginProjfair(token: String) {
        projfairToken = token
    }

    fun logoutProjfair() {
        projfairToken = null
        _candidate.value = null
        _participationsList.value = listOf()
    }

    var projfairToken: String?
        get() {
            return _sharedPreference.getString(PROJFAIR_TOKEN, null)
        }
        set(value) {
            with(_sharedPreference.edit()) {
                if (value == null) {
                    remove(PROJFAIR_TOKEN)
                } else {
                    putString(PROJFAIR_TOKEN, value)
                }
                apply()
                setAuth()
            }
        }

    var userType: UserStatus?
        get() {
            return _sharedPreference.getString(USER_TYPE, null).toUserStatusEnum()
        }
        set(value) {
            with(_sharedPreference.edit()) {
                if (value == null) {
                    remove(USER_TYPE)
                } else {
                    putString(USER_TYPE, value.toString())
                }
                apply()
            }
        }

    var userId: Int?
        get() {
            return _sharedPreference.getInt(USER_ID, 0)
        }
        set(value) {
            with(_sharedPreference.edit()) {
                if (value == null) {
                    remove(USER_ID)
                } else {
                    putInt(USER_ID, value)
                }
                apply()
            }
        }

    var userDescription: String?
        get() {
            return _sharedPreference.getString(USER_DESCRIPTION, "")
        }
        set(value) {
            with(_sharedPreference.edit()) {
                if (value == null) {
                    remove(USER_DESCRIPTION)
                } else {
                    putString(USER_DESCRIPTION, value)
                }
                apply()
            }
        }

    companion object {
        const val PROJFAIR_TOKEN = "projfairToken"
        const val USER_TYPE = "userType"
        const val USER_ID = "userId"
        const val USER_DESCRIPTION = "userDescription"
    }
}
