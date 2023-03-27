package com.istu.schedule.ui.page.projfair.candidate.participations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.domain.usecase.projfair.GetParticipationsListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import com.istu.schedule.util.addNewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CandidateParticipationsListViewModel @Inject constructor(
    private val _participationsUseCase: GetParticipationsListUseCase,
    private val _user: User,
) : BaseViewModel() {

    private val _participationsList = MutableLiveData<MutableList<Participation>>()
    val participationsList: LiveData<MutableList<Participation>> = _participationsList

    init {
        getParticipationsList()
    }

    private fun getParticipationsList() {
        _user.projfairToken?.let { token ->
            call({
                _participationsUseCase.getProjectStatesListList(token)
            }, onSuccess = {
                for (participation in it) {
                    if (participation.stateId != 3 && participation.stateId != 4) {
                        _participationsList.addNewItem(participation)
                    }
                }
            })
        }
    }
}
