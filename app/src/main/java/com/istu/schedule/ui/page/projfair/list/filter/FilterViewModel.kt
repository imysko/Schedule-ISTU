package com.istu.schedule.ui.page.projfair.list.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.model.ProjfairFiltersState
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.projfair.Skill
import com.istu.schedule.domain.model.projfair.Speciality
import com.istu.schedule.domain.usecase.projfair.GetSkillsListUseCase
import com.istu.schedule.domain.usecase.projfair.GetSpecialitiesListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val _specialitiesUseCase: GetSpecialitiesListUseCase,
    private val _skillsUseCase: GetSkillsListUseCase,
    private val _user: User,
) : BaseViewModel() {

    private val _skillsList = MutableLiveData<MutableList<Skill>>()
    val skillsList: LiveData<MutableList<Skill>> = _skillsList

    private val _specialitiesList = MutableLiveData<MutableList<Speciality>>()
    val specialitiesList: LiveData<MutableList<Speciality>> = _specialitiesList

    private val _filtersPageUiState = MutableStateFlow(FiltersPageUiState())
    val filtersPageUiState: StateFlow<FiltersPageUiState> = _filtersPageUiState.asStateFlow()

    fun loadFilters() {
        _filtersPageUiState.update {
            it.copy(
                statusesList = _user.projfairFiltersState.value.statusesList,
                specialitiesList = _user.projfairFiltersState.value.specialitiesList,
                skillsList = _user.projfairFiltersState.value.skillsList,
                difficultiesList = _user.projfairFiltersState.value.difficultiesList,
            )
        }
    }

    fun getSkillsList() {
        call({
            _skillsUseCase.getSkillsListList(
                token = _user.projfairToken ?: "",
            )
        }, onSuccess = {
            _skillsList.value = it.toMutableList()
        })
    }

    fun getSpecialitiesList() {
        call({
            _specialitiesUseCase.getSpecialitiesList(
                token = _user.projfairToken ?: "",
            )
        }, onSuccess = {
            _specialitiesList.value = it.toMutableList()
        })
    }

    fun setStatusesList(statusesList: List<Int>) {
        _filtersPageUiState.update { it.copy(statusesList = statusesList) }
    }

    fun setDifficultiesList(difficultiesList: List<Int>) {
        _filtersPageUiState.update { it.copy(difficultiesList = difficultiesList) }
    }

    fun setSpecialitiesList(specialitiesList: List<Pair<Int, String>>) {
        _filtersPageUiState.update { it.copy(specialitiesList = specialitiesList) }
    }

    fun setSkillsList(skillsList: List<Pair<Int, String>>) {
        _filtersPageUiState.update { it.copy(skillsList = skillsList) }
    }

    fun setSkillSearchText(text: String) {
        _filtersPageUiState.update { it.copy(skillSearchText = text) }
    }

    fun setSpecialitySearchText(text: String) {
        _filtersPageUiState.update { it.copy(specialitySearchText = text) }
    }

    fun saveFilters() {
        _user.setProjfairFilters(
            ProjfairFiltersState(
                true,
                _filtersPageUiState.value.statusesList,
                _filtersPageUiState.value.difficultiesList,
                _filtersPageUiState.value.specialitiesList,
                _filtersPageUiState.value.skillsList,
            ),
        )
    }

    fun resetFilters() {
        _filtersPageUiState.update {
            it.copy(
                statusesList = listOf(),
                specialitiesList = listOf(),
                skillsList = listOf(),
                difficultiesList = listOf(),
                skillSearchText = "",
                specialitySearchText = "",
            )
        }
    }
}

data class FiltersPageUiState(
    val statusesList: List<Int> = listOf(),
    val difficultiesList: List<Int> = listOf(),
    val specialitiesList: List<Pair<Int, String>> = listOf(),
    val skillsList: List<Pair<Int, String>> = listOf(),
    val skillSearchText: String = "",
    val specialitySearchText: String = "",
)
