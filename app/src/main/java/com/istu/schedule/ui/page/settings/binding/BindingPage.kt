package com.istu.schedule.ui.page.settings.binding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.istu.schedule.util.collectAsStateValue

@Composable
fun BindingPage(
    navController: NavHostController,
    viewModel: BindingViewModel = hiltViewModel()
) {
    val bindingUiState = viewModel.bindingUiState.collectAsStateValue()

    val instituteList by viewModel.institutesList.observeAsState(initial = emptyList())
    val coursesList by viewModel.coursesList.observeAsState(initial = emptyList())

    val teachersTips by viewModel.teachersTips.observeAsState(initial = emptyList())

    if (bindingUiState.isShowChooseUserStatusPage) {
        ChooseUserStatus(
            selectedGroupDescription = bindingUiState.selectedGroupDescription,
            selectedTeacherDescription = bindingUiState.selectedTeacherDescription,
            onBackClick = { navController.popBackStack() },
            selectUserStatus = { viewModel.selectUserStatus(it) }
        )
    }
    else if (bindingUiState.isShowChooseInstitutePage) {
        ChooseInstitute(
            institutesList = instituteList,
            onBackClick = { viewModel.onClickBackToChooseUserState() },
            onChooseInstitute = { viewModel.selectInstitute(it) }
        )
    }
    else if (bindingUiState.isShowChooseGroupPage) {
        ChooseGroup(
            instituteTitle = bindingUiState.selectedInstituteDescription!!,
            courseList = coursesList,
            onBackClick = { viewModel.onClickBackToChooseInstitute() },
            onChooseGroup = { viewModel.selectGroup(it) }
        )
    }
    else if (bindingUiState.isShowChooseTeacherPage) {
        ChooseTeacher(
            onBackClick = { viewModel.onClickBackToChooseUserState() },
            teachersList = teachersTips,
            onChooseTeacher = { viewModel.selectTeacher(it) },
            onValueChange = { viewModel.onTeacherInput(it) },
        )
    }
}
