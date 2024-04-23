package com.istu.schedule.ui.page.projfair.list.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.User
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import me.progneo.projfair.domain.model.FiltersState
import me.progneo.projfair.domain.model.Skill
import me.progneo.projfair.domain.model.Speciality
import me.progneo.projfair.domain.usecase.GetSkillListUseCase
import me.progneo.projfair.domain.usecase.GetSpecialityListUseCase

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getSpecialtyListUseCase: GetSpecialityListUseCase,
    private val getSkillListUseCase: GetSkillListUseCase,
    private val user: User
) : BaseViewModel() {

    private val _skillsList = MutableLiveData<MutableList<Skill>>()
    val skillsList: LiveData<MutableList<Skill>> = _skillsList

    private val _specialitiesList = MutableLiveData<MutableList<Speciality>>()
    val specialitiesList: LiveData<MutableList<Speciality>> = _specialitiesList

    fun getFilters(): FiltersState = user.projfairFiltersState.value

    fun setFilters(projfairFiltersState: FiltersState) =
        user.setProjfairFilters(projfairFiltersState)

    fun getSkillsList() {
        call(
            apiCall = { getSkillListUseCase() },
            onSuccess = { _skillsList.value = it.toMutableList() }
        )
    }

    fun getSpecialitiesList() {
        call(
            apiCall = { getSpecialtyListUseCase() },
            onSuccess = { _specialitiesList.value = it.toMutableList() }
        )
    }
}
