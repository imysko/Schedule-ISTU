package com.istu.schedule.ui.page.settings.schedule

import com.istu.schedule.domain.entities.Subgroup

sealed class SettingScheduleUiState {
    data class MainScheduleSettings(
        val selectedGroupDescription: String? = null,
        val selectedTeacherDescription: String? = null,
        val isSubgroupSettingAvailable: Boolean = false,
        val subgroup: Subgroup = Subgroup.ALL,
    ): SettingScheduleUiState()

    data object ChooseInstituteState: SettingScheduleUiState()

    data class ChooseGroupState(
        val instituteTitle: String,
    ): SettingScheduleUiState()

    data class ChooseSubgroupState(
        val selectedSubgroup: Subgroup,
    ): SettingScheduleUiState()

    data object ChooseTeacherState: SettingScheduleUiState()
}
