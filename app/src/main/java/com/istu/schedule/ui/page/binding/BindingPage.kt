package com.istu.schedule.ui.page.binding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.DisplayText
import com.istu.schedule.ui.components.base.FeedbackIconButton
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.page.binding.options.ChooseCourse
import com.istu.schedule.ui.page.binding.options.ChooseGroup
import com.istu.schedule.ui.page.binding.options.ChooseInstitute
import com.istu.schedule.ui.page.binding.options.ChooseUserStatus

@Composable
fun BindingPage(
    navController: NavHostController,
    viewModel: BindingViewModel = hiltViewModel(),
) {
    val instituteList by viewModel.institutesList.observeAsState(initial = emptyList())
    val coursesList by viewModel.coursesList.observeAsState(initial = emptyList())
    val groupsList by viewModel.groupsList.observeAsState(initial = emptyList())

    val endOfListReached by remember {
        derivedStateOf { }
    }

    LaunchedEffect(endOfListReached) {
        viewModel.getInstitutesList()
    }

    AppComposable(
        viewModel = viewModel,
        content = {
            SIScaffold(
                navigationIcon = {
                    FeedbackIconButton(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = MaterialTheme.colorScheme.onSurface
                    ) {
                        navController.popBackStack()
                    }
                }
            ) {
                DisplayText(
                    text = "Binding",
                    desc = ""
                )

                ChooseUserStatus()
                ChooseInstitute(
                    instituteList = instituteList,
                    onChoose = {
                        viewModel.chooseInstitute(it)
                    }
                )
                ChooseCourse(
                    courseList = coursesList,
                    onChoose = {
                        viewModel.chooseCourse(it)
                    }
                )
                ChooseGroup(
                    groupList = groupsList,
                    onChoose = { }
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun BindingPagePreview() {
    BindingPage(rememberNavController())
}
