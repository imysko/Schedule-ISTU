package com.istu.schedule.ui.page.projfair.list.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.api.entities.projfair.ProjfairFiltersState
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.entities.projfair.Skill
import com.istu.schedule.domain.entities.projfair.Speciality
import com.istu.schedule.domain.usecase.projfair.GetSkillsListUseCase
import com.istu.schedule.domain.usecase.projfair.GetSpecialitiesListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val _specialitiesUseCase: GetSpecialitiesListUseCase,
    private val _skillsUseCase: GetSkillsListUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _skillsList = MutableLiveData<MutableList<Skill>>()
    val skillsList: LiveData<MutableList<Skill>> = _skillsList

    private val _specialitiesList = MutableLiveData<MutableList<Speciality>>()
    val specialitiesList: LiveData<MutableList<Speciality>> = _specialitiesList

    fun getFilters(): ProjfairFiltersState = _user.projfairFiltersState.value

    fun setFilters(projfairFiltersState: ProjfairFiltersState) =
        _user.setProjfairFilters(projfairFiltersState)

    fun getSkillsList() {
        call({
            _skillsUseCase.getSkillsListList(
                token = _user.projfairToken ?: ""
            )
        }, onSuccess = {
            _skillsList.value = it.toMutableList()
        })
    }

    fun getSpecialitiesList() {
        call({
            _specialitiesUseCase.getSpecialitiesList(
                token = _user.projfairToken ?: ""
            )
        }, onSuccess = {
            _specialitiesList.value = it.toMutableList()
        })
    }
}
