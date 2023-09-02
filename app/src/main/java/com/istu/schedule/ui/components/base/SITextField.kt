package com.istu.schedule.ui.components.base

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.icons.Check
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Green
import com.istu.schedule.ui.theme.Shape10

@Composable
fun SITextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    placeholder: String,
    tailingIcon: (@Composable () -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(Shape10)
            .border(1.dp, AppTheme.colorScheme.secondary, Shape10)
    ) {
        BasicTextField(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            textStyle = AppTheme.typography.bodyMedium,
            value = value,
            enabled = enabled,
            onValueChange = {
                onValueChange(it)
            },
            readOnly = readOnly,
            singleLine = true,
            cursorBrush = SolidColor(AppTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier.defaultMinSize(minHeight = 25.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                modifier = Modifier.alpha(0.7f),
                                text = placeholder,
                                style = AppTheme.typography.bodyMedium
                            )
                        }
                        innerTextField()
                    }
                    tailingIcon?.let { it() }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSITextFieldEmpty() {
    AppTheme {
        SITextField(
            value = "",
            modifier = Modifier.padding(10.dp),
            placeholder = "Select speciality",
            enabled = false,
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
    AppTheme {
        SITextField(
            value = "Text",
            modifier = Modifier.padding(10.dp),
            placeholder = "Select speciality",
            enabled = false,
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
    AppTheme {
        SITextField(
            value = "",
            modifier = Modifier.padding(10.dp),
            readOnly = true,
            placeholder = "Select speciality",
            enabled = false,
            onValueChange = {},
            tailingIcon = {
                Icon(imageVector = Icons.Check, tint = Green, contentDescription = null)
            }
        )
    }
}
