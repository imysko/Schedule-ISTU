package com.istu.schedule.ui.page.settings.binding.options

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.model.schedule.Course

@Deprecated("Will be removed")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseCourse(
    enabled: Boolean = true,
    selectedCourseText: String = "",
    courseList: List<Course> = emptyList(),
    onChoose: (chosenCourse: Course) -> Unit,
    onClear: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(selectedCourseText) }

    Surface(
        modifier = Modifier
            .padding(horizontal = 24.dp),
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                enabled = enabled,
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                trailingIcon = {
                    if (enabled) {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    } else {
                        IconButton(
                            onClick = { onClear() },
                            colors = IconButtonDefaults.outlinedIconButtonColors(
                                contentColor = Color.Red,
                            ),
                            content = {
                                Icon(Icons.Outlined.Clear, "clear")
                            },
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.choose_course),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(),
            )
            ExposedDropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(),
                expanded = if (enabled) expanded else false,
                onDismissRequest = {
                    expanded = false
                },
            ) {
                courseList.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption.courseNumber.toString()) },
                        onClick = {
                            selectedOptionText = selectionOption.courseNumber.toString()
                            expanded = false
                            onChoose(selectionOption)
                        },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun ChooseCoursePreview() {
    ChooseCourse(
        onChoose = { },
        onClear = { },
    )
}
