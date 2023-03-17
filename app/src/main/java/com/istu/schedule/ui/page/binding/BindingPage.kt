package com.istu.schedule.ui.page.binding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.DisplayText
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.page.binding.options.ChooseCourse
import com.istu.schedule.ui.page.binding.options.ChooseGroup
import com.istu.schedule.ui.page.binding.options.ChooseInstitute
import com.istu.schedule.ui.page.binding.options.ChooseUserStatus

@Composable
fun BindingPage(
    viewModel: BindingViewModel = hiltViewModel()
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
            SIScaffold {
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
    BindingPage()
}
