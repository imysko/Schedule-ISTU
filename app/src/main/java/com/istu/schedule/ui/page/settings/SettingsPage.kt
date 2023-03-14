package com.istu.schedule.ui.page.settings

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.istu.schedule.R
import com.istu.schedule.data.preference.LocalLanguages
import com.istu.schedule.ui.components.base.DisplayText
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.page.settings.language.LanguageDialog

@Composable
fun SettingsPage() {
    val languages = LocalLanguages.current
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    SIScaffold(
        content = {
            LazyColumn {
                item {
                    DisplayText(
                        text = remember(configuration.locales) {
                            context.resources.getString(R.string.settings)
                        },
                        desc = ""
                    )
                }
                item {
                    SettingItem(
                        title = remember(configuration.locales) {
                            context.resources.getString(R.string.group)
                        },
                        description = "ИСТб-20-3",
                        icon = Icons.Outlined.Groups
                    ) {
                    }
                }
                item {
                    SettingItem(
                        title = remember(configuration.locales) {
                            context.resources.getString(R.string.projfair)
                        },
                        description = remember(configuration.locales) {
                            context.resources.getString(R.string.login)
                        },
                        icon = Icons.Outlined.Work
                    ) {
                    }
                }
                item {
                    val openDialog = remember { mutableStateOf(false)  }

                    if (openDialog.value) {
                        LanguageDialog(
                            isOpenDialog = openDialog
                        )
                    }

                    SettingItem(
                        title = remember(configuration.locales) {
                            context.resources.getString(R.string.language)
                        },
                        description = languages.toDescription(context),
                        icon = Icons.Default.Language
                    ) {
                        openDialog.value = true
                    }
                }
                item {
                    SettingItem(
                        title = remember(configuration.locales) {
                            context.resources.getString(R.string.theme)
                        },
                        description = remember(configuration.locales) {
                            context.resources.getString(R.string.light)
                        },
                        icon = Icons.Outlined.Palette
                    ) {
                    }
                }
                item {
                    SettingItem(
                        title = remember(configuration.locales) {
                            context.resources.getString(R.string.developers)
                        },
                        icon = Icons.Outlined.Engineering
                    ) {
                    }
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun SettingsPagePreview() {
    SettingsPage()
}