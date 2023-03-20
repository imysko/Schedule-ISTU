package com.istu.schedule.ui.page.settings.binding.options

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.istu.schedule.domain.model.schedule.Group

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseGroup(
    enabled: Boolean = true,
    selectedGroupText: String = "",
    groupList: List<Group> = emptyList(),
    onChoose: (chosenGroup: Group) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(selectedGroupText) }

    Surface(
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                if (enabled) {
                    expanded = !expanded
                }
            }
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
                    }
                },
                label = {
                    Text(
                        text = "Choose your group",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )
            ExposedDropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(),
                expanded = if (enabled) expanded else false,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                groupList.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption.name!!) },
                        onClick = {
                            selectedOptionText = selectionOption.name!!
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
fun ChooseGroupPreview() {
    ChooseGroup(onChoose = { })
}