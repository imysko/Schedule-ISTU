package com.istu.schedule.ui.page.binding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.ui.theme.Shape20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseUserStatus() {
    val options = listOf(
        stringResource(id = R.string.is_student),
        stringResource(id = R.string.is_teacher)
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var textFieldValue by remember { mutableStateOf(options[selectedIndex]) }

    Surface(
        modifier = Modifier
            .height(56.dp)
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        shape = Shape20,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextField(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = { expanded = !expanded }
                        ) {
                            Icon(
                                Icons.Filled.ArrowDropDown,
                                "dropdown"
                            )
                        }
                    },
                    readOnly = true,
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    singleLine = true
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = {
                        Text(text = option)
                    },
                    onClick = {
                        selectedIndex = index
                        textFieldValue = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ChooseUserStatusPreview() {
    ChooseUserStatus()
}