package com.istu.schedule.ui.page.settings.language

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.istu.schedule.R
import com.istu.schedule.data.preference.LanguagesPreference
import com.istu.schedule.data.preference.LocalLanguages
import com.istu.schedule.ui.components.base.button.radio.RadioButtonWithText
import com.istu.schedule.ui.theme.ScheduleISTUTheme

@Composable
fun LanguageDialog(
    isOpenDialog: MutableState<Boolean>
) {
    val context = LocalContext.current
    val languages = LocalLanguages.current
    val scope = rememberCoroutineScope()

    val configuration = LocalConfiguration.current

    AlertDialog(
        title = {
            Text(
                text = remember(configuration.locales) {
                    context.resources.getString(R.string.language)
                }
            )
        },
        text = {
            Column {
                LanguagesPreference.values.map {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButtonWithText(
                            text = it.toDescription(context),
                            selected = it == languages,
                            onSelect = {
                                it.put(context, scope)
                            }
                        )
                    }
                }
            }
        },
        onDismissRequest = {
            isOpenDialog.value = false
        },
        confirmButton = {
            TextButton(
                onClick = {
                    isOpenDialog.value = false
                }
            ) {
                Text(
                    text = remember(configuration.locales) {
                        context.resources.getString(R.string.close)
                    }
                )
            }
        },
        dismissButton = {
        }
    )
}

@Composable
@Preview(showBackground = true)
fun LanguageDialogPreview() {
    ScheduleISTUTheme {
        LanguageDialog(isOpenDialog = remember { mutableStateOf(true) })
    }
}
