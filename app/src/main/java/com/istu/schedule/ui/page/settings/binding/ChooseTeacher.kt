package com.istu.schedule.ui.page.settings.binding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.model.schedule.Teacher
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape5
import com.istu.schedule.ui.theme.ShapeTop15

@Composable
fun ChooseTeacher(
    onBackClick: () -> Unit,
    teachersList: List<Teacher> = emptyList(),
    onValueChange: (value: String) -> Unit,
    onChooseTeacher: (chosenTeacher: Teacher) -> Unit
) {
    var value by remember { mutableStateOf("") }

    BackHandler {
        onBackClick()
    }

    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.account),
                isShowBackButton = true,
                onBackPressed = { onBackClick() }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .clip(ShapeTop15)
                    .background(AppTheme.colorScheme.backgroundSecondary),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                SearchLine(
                    modifier = Modifier.padding(top = 9.dp, start = 15.dp, end = 15.dp),
                    value = value,
                    isError = !teachersList.any() && value.any(),
                    onValueChange = { input ->
                        value = input
                        onValueChange(input)
                    }
                )

                TeachersList(
                    teachersList = teachersList,
                    onChooseTeacher = { teacher -> onChooseTeacher(teacher) }
                )
            }
        }
    )
}

@Composable
fun SearchLine(
    modifier: Modifier = Modifier,
    value: String = "",
    isError: Boolean = false,
    onValueChange: (value: String) -> Unit,
    onDone: () -> Unit = { }
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        isError = isError,
        onValueChange = { onValueChange(it) },
        textStyle = AppTheme.typography.subtitle,
        singleLine = true,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_teacher),
                style = AppTheme.typography.subtitle.copy(
                    color = AppTheme.colorScheme.secondary
                )
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Rounded.Search,
                tint = AppTheme.colorScheme.secondary,
                contentDescription = stringResource(id = R.string.search)
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                onDone()
            }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = AppTheme.colorScheme.backgroundSecondary,
            unfocusedContainerColor = AppTheme.colorScheme.backgroundSecondary
        ),
        shape = RoundedCornerShape(10.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeachersList(
    teachersList: List<Teacher> = emptyList(),
    onChooseTeacher: (chosenTeacher: Teacher) -> Unit
) {
    val groupedTeachersList = teachersList.groupBy { it.fullName[0].toString() }

    AppTheme {
        if (teachersList.any()) {
            LazyColumn(
                modifier = Modifier.padding(start = 25.dp, end = 15.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                groupedTeachersList.forEach { (letter, teachers) ->
                    stickyHeader {
                        Text(
                            text = letter,
                            style = AppTheme.typography.title.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    items(teachers) { teacher ->
                        Row(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 20.dp)
                                .clip(Shape5)
                                .clickable { onChooseTeacher(teacher) },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = teacher.fullName,
                                style = AppTheme.typography.subtitle.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(64.dp))
                    Spacer(
                        modifier = Modifier.windowInsetsBottomHeight(
                            WindowInsets.navigationBars
                        )
                    )
                }
            }
        } else {
            Text(
                modifier = Modifier.padding(start = 25.dp, end = 15.dp),
                text = stringResource(id = R.string.not_found),
                style = AppTheme.typography.subtitle.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
@Preview
fun SearchLineErrorPreview() {
    AppTheme {
        SearchLine(
            value = "",
            isError = true,
            onValueChange = { }
        ) { }
    }
}

@Composable
@Preview(showBackground = true, locale = "ru")
fun ChooseTeacherPreview() {
    AppTheme {
        ChooseTeacher(
            onBackClick = { },
            teachersList = listOf(
                Teacher(
                    teacherId = 0,
                    fullName = "Аршинский Вадим Леонидович",
                    shortname = ""
                ),
                Teacher(
                    teacherId = 0,
                    fullName = "Копайгородский Алексей Николаевич",
                    shortname = ""
                ),
                Teacher(
                    teacherId = 0,
                    fullName = "Копайгородский Алексей Николаевич",
                    shortname = ""
                ),
                Teacher(
                    teacherId = 0,
                    fullName = "Копайгородский Алексей Николаевич",
                    shortname = ""
                ),
                Teacher(
                    teacherId = 0,
                    fullName = "Копайгородский Алексей Николаевич",
                    shortname = ""
                )
            ),
            onValueChange = { },
            onChooseTeacher = { }
        )
    }
}
