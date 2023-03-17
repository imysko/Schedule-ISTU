package com.istu.schedule.ui.page.binding.options

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.domain.model.schedule.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseCourse(
    courseList: List<Course> = emptyList(),
    onChoose: (chosenCourse: Course) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                label = {
                    Text(
                        text = "Choose your course",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )
            ExposedDropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                courseList.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption.courseNumber.toString()) },
                        onClick = {
                            selectedOptionText = selectionOption.courseNumber.toString()
                            expanded = false
                            onChoose(selectionOption)
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ChooseCoursePreview() {
    ChooseInstitute(onChoose = { })
}