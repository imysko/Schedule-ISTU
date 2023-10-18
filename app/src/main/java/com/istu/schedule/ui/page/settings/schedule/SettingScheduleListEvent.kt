package com.istu.schedule.ui.page.settings.schedule

import com.istu.schedule.data.enums.Subgroup
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.domain.model.schedule.Group
import com.istu.schedule.domain.model.schedule.Institute
import com.istu.schedule.domain.model.schedule.Teacher

sealed class SettingScheduleListEvent {
    data class SelectUserStatus(val status: UserStatus): SettingScheduleListEvent()
    data class SelectInstitute(val institute: Institute): SettingScheduleListEvent()
    data class SelectGroup(val group: Group): SettingScheduleListEvent()
    data object NavigateToSubgroupSelection: SettingScheduleListEvent()
    data class SelectSubgroup(val subgroup: Subgroup): SettingScheduleListEvent()
    data class SelectTeacher(val teacher: Teacher): SettingScheduleListEvent()
    data class FilterTeacherList(val value: String): SettingScheduleListEvent()
    data object OnBackClickToScheduleSettings: SettingScheduleListEvent()
    data object OnBackClickToChooseInstitute: SettingScheduleListEvent()
}
