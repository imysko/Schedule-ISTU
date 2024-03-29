package com.istu.schedule.ui.components.base

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.Shape20

@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun SIDropdownMenu(
    modifier: Modifier = Modifier,
    textValue: String = "",
    placeholder: String,
    listItems: List<Pair<Int, String>>,
    selectedItems: MutableList<Pair<Int, String>>,
    onTextValueChange: (String) -> Unit = {},
    onItemSelect: (List<Pair<Int, String>>) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier
            .clip(Shape10)
            .border(1.dp, AppTheme.colorScheme.secondary, Shape10),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FlowRow(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                selectedItems.forEach { pair ->
                    SIInputChip(
                        text = pair.second,
                        onClick = {
                            selectedItems.remove(pair)
                            onItemSelect(selectedItems)
                        }
                    )
                }
                Box(
                    modifier = Modifier.weight(1f).defaultMinSize(minHeight = 25.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BasicTextField(
                        textStyle = AppTheme.typography.bodyMedium,
                        value = textValue,
                        onValueChange = {
                            onTextValueChange(it)
                            expanded = true
                        },
                        singleLine = true,
                        cursorBrush = SolidColor(AppTheme.colorScheme.primary),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.defaultMinSize(minHeight = 25.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (textValue.isEmpty()) {
                                    Text(
                                        modifier = Modifier.alpha(0.7f),
                                        text = placeholder,
                                        style = AppTheme.typography.bodyMedium
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }
            }
            TrailingIcon(expanded = false)
        }

        val filteredOptions = listItems.filter {
            it.second
                .contains(textValue, ignoreCase = true)
                .and(!selectedItems.contains(it))
        }

        if (filteredOptions.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                filteredOptions.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.second) },
                        onClick = {
                            selectedItems.add(item)
                            onTextValueChange("")
                            onItemSelect(selectedItems)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSIDropdownMenu() {
    AppTheme {
        SIDropdownMenu(
            modifier = Modifier.padding(10.dp),
            placeholder = "Select speciality",
            listItems = mutableListOf(
                Pair(1, "Android"),
                Pair(2, "JavaScript"),
                Pair(3, "Kotlin"),
                Pair(4, "C#")
            ),
            selectedItems = mutableListOf(Pair(1, "Android"), Pair(2, "JavaScript"))
        )
    }
}
