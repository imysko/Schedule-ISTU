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
import com.istu.schedule.domain.model.schedule.Teacher

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTeacher(
    enabled: Boolean = true,
    selectedTeacherText: String = "",
    teachersTips: List<Teacher>,
    onTextChanged: (String) -> Unit,
    onChoose: (chosenTeacher: Teacher) -> Unit,
    onClear: () -> Unit
) {
    var inputtedText by remember { mutableStateOf(selectedTeacherText) }
    var expanded by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

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
                enabled = enabled,
                value = inputtedText,
                onValueChange = {
                    isError = !teachersTips.any()
                    inputtedText = it
                    onTextChanged(it)
                },
                isError = isError && inputtedText.length > 2,
                supportingText = {
                    if (isError && inputtedText.length > 2) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Teacher not found",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    if (!enabled) {
                        IconButton(
                            onClick = { onClear() },
                            colors = IconButtonDefaults.outlinedIconButtonColors(
                                contentColor = Color.Red
                            ),
                            content = {
                                Icon(Icons.Outlined.Clear, "clear")
                            }
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.fullname),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )
            ExposedDropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(),
                expanded = if (inputtedText.length > 2 && teachersTips.any()) expanded else false,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                teachersTips.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption.fullname) },
                        onClick = {
                            inputtedText = selectionOption.fullname
                            expanded = false
                            onChoose(selectionOption)
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun InputTeacherPreview() {
    InputTeacher(
        teachersTips = emptyList(),
        onTextChanged = { },
        onChoose = { },
        onClear = { }
    )
}
