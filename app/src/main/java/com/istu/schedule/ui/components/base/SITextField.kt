package com.istu.schedule.ui.components.base

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.icons.Check
import com.istu.schedule.ui.theme.Green
import com.istu.schedule.ui.theme.ScheduleISTUTheme

@Composable
fun SITextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    placeholder: String,
    tailingIcon: (@Composable () -> Unit)? = null
) {
    BasicTextField(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(4.dp))
            .padding(10.dp),
        textStyle = MaterialTheme.typography.bodyMedium,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        readOnly = readOnly,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box {
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.alpha(0.7f),
                            text = placeholder,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
                tailingIcon?.let { it() }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSITextFieldEmpty() {
    ScheduleISTUTheme {
        SITextField(
            value = "",
            modifier = Modifier.padding(10.dp),
            placeholder = "Select speciality",
            onValueChange = {},
            tailingIcon = {
                Icon(imageVector = Icons.Check, tint = Green, contentDescription = null)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSITextFieldNotEmpty() {
    ScheduleISTUTheme {
        SITextField(
            value = "Text",
            modifier = Modifier.padding(10.dp),
            placeholder = "Select speciality",
            onValueChange = {},
            tailingIcon = {
                Icon(imageVector = Icons.Check, tint = Green, contentDescription = null)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSITextFieldReadOnly() {
    ScheduleISTUTheme {
        SITextField(
            value = "",
            modifier = Modifier.padding(10.dp),
            readOnly = true,
            placeholder = "Select speciality",
            onValueChange = {},
            tailingIcon = {
                Icon(imageVector = Icons.Check, tint = Green, contentDescription = null)
            }
        )
    }
}
