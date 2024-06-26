package com.istu.schedule.ui.page.settings.schedule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.istu.schedule.R
import com.istu.schedule.domain.entities.schedule.Course
import com.istu.schedule.domain.entities.schedule.Institute
import com.istu.schedule.domain.entities.schedule.Teacher
import com.istu.schedule.ui.page.ext.NoInternetPage
import com.istu.schedule.util.NetworkStatus

@Composable
fun SettingSchedulePage(
    navController: NavHostController,
    viewModel: SettingScheduleViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val instituteList by viewModel.institutesList.observeAsState(initial = emptyList())
    val coursesList by viewModel.coursesList.observeAsState(initial = emptyList())

    val teachersTips by viewModel.teachersTips.observeAsState(initial = emptyList())

    val networkStatus by viewModel.networkStatus.observeAsState(initial = NetworkStatus.Available)
    val isLoading by viewModel.loading.observeAsState(initial = false)

    SettingScheduleContent(
        networkStatus = networkStatus,
        isLoading = isLoading,
        uiState = uiState,
        instituteList = instituteList,
        coursesList = coursesList,
        teachersTips = teachersTips,
        onEvent = viewModel::onEvent,
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
private fun SettingScheduleContent(
    networkStatus: NetworkStatus,
    isLoading: Boolean,
    uiState: SettingScheduleUiState,
    instituteList: List<Institute>,
    coursesList: List<Course>,
    teachersTips: List<Teacher>,
    onEvent: (SettingScheduleListEvent) -> Unit,
    onBackClick: () -> Unit
) {
    if (networkStatus == NetworkStatus.Unavailable) {
        NoInternetPage(
            title = stringResource(R.string.account),
            isShowBackButton = true,
            onBackPressed = { onBackClick() }
        )
    } else if (networkStatus == NetworkStatus.Available) {
        when (uiState) {
            is SettingScheduleUiState.MainScheduleSettings -> {
                MainScheduleSettings(
                    selectedGroupDescription = uiState.selectedGroupDescription,
                    selectedTeacherDescription = uiState.selectedTeacherDescription,
                    isSubgroupSettingAvailable = uiState.isSubgroupSettingAvailable,
                    subgroup = uiState.subgroup,
                    onBackClick = { onBackClick() },
                    selectUserStatus = { onEvent(SettingScheduleListEvent.SelectUserStatus(it)) },
                    onSubgroupSettingClick = {
                        onEvent(SettingScheduleListEvent.NavigateToSubgroupSelection)
                    }
                )
            }
            SettingScheduleUiState.ChooseInstituteState -> {
                ChooseInstitute(
                    isLoading = isLoading,
                    institutesList = instituteList,
                    onBackClick = {
                        onEvent(SettingScheduleListEvent.OnBackClickToScheduleSettings)
                    },
                    onChooseInstitute = { onEvent(SettingScheduleListEvent.SelectInstitute(it)) }
                )
            }
            is SettingScheduleUiState.ChooseGroupState -> {
                ChooseGroup(
                    instituteTitle = uiState.instituteTitle,
                    courseList = coursesList,
                    onBackClick = {
                        onEvent(SettingScheduleListEvent.OnBackClickToChooseInstitute)
                    },
                    onChooseGroup = { onEvent(SettingScheduleListEvent.SelectGroup(it)) }
                )
            }
            is SettingScheduleUiState.ChooseSubgroupState -> {
                ChooseSubgroup(
                    selectedSubgroup = uiState.selectedSubgroup,
                    onBackClick = {
                        onEvent(SettingScheduleListEvent.OnBackClickToScheduleSettings)
                    },
                    onChooseSubgroup = { onEvent(SettingScheduleListEvent.SelectSubgroup(it)) }
                )
            }
            SettingScheduleUiState.ChooseTeacherState -> {
                ChooseTeacher(
                    isLoading = isLoading,
                    teachersList = teachersTips,
                    onBackClick = {
                        onEvent(SettingScheduleListEvent.OnBackClickToScheduleSettings)
                    },
                    onChooseTeacher = { onEvent(SettingScheduleListEvent.SelectTeacher(it)) },
                    onValueChange = { onEvent(SettingScheduleListEvent.FilterTeacherList(it)) }
                )
            }
        }
    }
}
