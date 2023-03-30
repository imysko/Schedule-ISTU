package com.istu.schedule.ui.page.settings.binding

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material.icons.rounded.Rule
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.R
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.BlockRadioButton
import com.istu.schedule.ui.components.base.BlockRadioGroupButtonItem
import com.istu.schedule.ui.components.base.DisplayText
import com.istu.schedule.ui.components.base.FeedbackIconButton
import com.istu.schedule.ui.components.base.SIDialog
import com.istu.schedule.ui.components.base.SIExtensibleVisibility
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.components.base.Subtitle
import com.istu.schedule.ui.page.settings.binding.options.ChooseCourse
import com.istu.schedule.ui.page.settings.binding.options.ChooseGroup
import com.istu.schedule.ui.page.settings.binding.options.ChooseInstitute
import com.istu.schedule.ui.page.settings.binding.options.InputTeacher
import com.istu.schedule.util.collectAsStateValue

@Composable
fun BindingPage(
    navController: NavHostController,
    viewModel: BindingViewModel = hiltViewModel(),
) {
    val bindingUiState = viewModel.bindingUiState.collectAsStateValue()

    var radioButtonSelected by remember {
        mutableStateOf(
            if (viewModel.userState.value != UserStatus.UNKNOWN) viewModel.userState.value.ordinal
            else -1
        )
    }

    val instituteList by viewModel.institutesList.observeAsState(initial = emptyList())
    val coursesList by viewModel.coursesList.observeAsState(initial = emptyList())
    val groupsList by viewModel.groupsList.observeAsState(initial = emptyList())
    val teachersTips by viewModel.teachersTips.observeAsState(initial = emptyList())

    val endOfListReached by remember {
        derivedStateOf { }
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
                },
                content = {
                    DisplayText(
                        text = "Binding",
                        desc = ""
                    )
                    Subtitle(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = stringResource(id = R.string.who_are_you)
                    )
                    BlockRadioButton(
                        selected = radioButtonSelected,
                        onSelected = { radioButtonSelected = it },
                        itemRadioGroups = listOf(
                            BlockRadioGroupButtonItem(
                                text = stringResource(R.string.is_student),
                                onClick = { viewModel.selectUserStatus(UserStatus.STUDENT) },
                            ) {
                                SIExtensibleVisibility(
                                    visible = bindingUiState.isShowInstitutesInput
                                ) {
                                    ChooseInstitute(
                                        enabled = bindingUiState.canChooseInstitute,
                                        selectedInstituteText = bindingUiState.selectedInstituteText,
                                        instituteList = instituteList,
                                        onChoose = {
                                            viewModel.selectInstitute(it)
                                        },
                                        onClear = {
                                            viewModel.clearInstitute()
                                        }
                                    )
                                }
                                SIExtensibleVisibility(
                                    visible = bindingUiState.isShowCoursesInput
                                ) {
                                    ChooseCourse(
                                        enabled = bindingUiState.canChooseCourse,
                                        selectedCourseText = bindingUiState.selectedCourseText,
                                        courseList = coursesList,
                                        onChoose = {
                                            viewModel.selectCourse(it)
                                        },
                                        onClear = {
                                            viewModel.clearCourse()
                                        }
                                    )
                                }
                                SIExtensibleVisibility(
                                    visible = bindingUiState.isShowGroupsInput
                                ) {
                                    ChooseGroup(
                                        enabled = bindingUiState.canChooseGroup,
                                        selectedGroupText = bindingUiState.selectedGroupText,
                                        groupList = groupsList,
                                        onChoose = {
                                            viewModel.selectGroup(it)
                                        },
                                        onClear = {
                                            viewModel.clearGroup()
                                        }
                                    )
                                }
                            },
                            BlockRadioGroupButtonItem(
                                text = stringResource(R.string.is_teacher),
                                onClick = { viewModel.selectUserStatus(UserStatus.TEACHER) },
                            ) {
                                SIExtensibleVisibility(
                                    visible = bindingUiState.isShowTeachersInput
                                ) {
                                    Subtitle(
                                        modifier = Modifier.padding(horizontal = 24.dp),
                                        text = "Teacher"
                                    )
                                    InputTeacher(
                                        enabled = bindingUiState.canEditTeacherName,
                                        selectedTeacherText = bindingUiState.selectedTeacherText,
                                        teachersTips = teachersTips,
                                        onTextChanged = {
                                            viewModel.inputText(it)
                                        },
                                        onChoose = { viewModel.selectTeacher(it) },
                                        onClear = { viewModel.clearTeacher() }
                                    )
                                }
                            },
                        ),
                    )
                },
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        modifier = Modifier.navigationBarsPadding(),
                        expanded = bindingUiState.isShowFloatingButton,
                        icon = {
                            Icon(
                                Icons.Rounded.CheckCircleOutline,
                                "Bind"
                            )
                        },
                        text = {
                            Text(
                                text = "Bind"
                            )
                        },
                        onClick = {
                            viewModel.bindPressed()

                            if (bindingUiState.canBinding) {
                                navController.popBackStack()
                            }
                        },
                    )
                }
            )
        }
    )

    SIDialog(
        visible = bindingUiState.isShowIncompleteInputDialog,
        onDismissRequest = { viewModel.changeDialogStatus(false) },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Rule,
                contentDescription = "Please complete the input"
            )
        },
        title = {
            Text(text = "Please complete the input")
        },
        confirmButton = {
            TextButton(
                onClick = { viewModel.changeDialogStatus(false) }
            ) {
                Text(text = "Ok")
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun BindingPagePreview() {
    BindingPage(rememberNavController())
}
