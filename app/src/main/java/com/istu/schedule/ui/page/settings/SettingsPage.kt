package com.istu.schedule.ui.page.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.istu.schedule.ui.components.base.SIScaffold

@Composable
fun SettingsPage() {
    SIScaffold(
        content = {
            LazyColumn {
                item {
                    Text(
                        text = "Settings",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
                item {
                    SettingItem(
                        title = "Group",
                        description = "ИСТб-20-3",
                        icon = Icons.Outlined.Groups
                    ) {

                    }
                }
                item {
                    SettingItem(
                        title = "Project Fair",
                        description = "Log in",
                        icon = Icons.Outlined.Work
                    ) {

                    }
                }
                item {
                    val openDialog = remember { mutableStateOf(false)  }
                    if (openDialog.value) {
                        val languages = mapOf(
                            "Use system language" to 0,
                            "English" to 1,
                            "Russian" to 2
                        )

                        val selectedLanguage = remember { mutableStateOf(0) }

                        AlertDialog(
                            title = {
                                Text("Language")
                            },
                            text = {
                                Column {
                                    languages.map {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            RadioButton(
                                                selected = it.value == selectedLanguage.value,
                                                onClick = {
                                                    selectedLanguage.value = it.value
                                                })
                                            Text(text = it.key)
                                        }
                                    }
                                }
                            },
                            onDismissRequest = {
                                openDialog.value = false
                            },
                            confirmButton = {
                                TextButton(onClick = {
                                    openDialog.value = false
                                } ) {
                                    Text("Close")
                                }
                            },
                            dismissButton = {}
                        )
                    }

                    SettingItem(
                        title = "Language",
                        description = "English",
                        icon = Icons.Default.Language
                    ) {
                        openDialog.value = true
                    }
                }
                item {
                    SettingItem(
                        title = "Theme",
                        description = "Light",
                        icon = Icons.Outlined.Palette
                    ) {

                    }
                }
                item {
                    SettingItem(
                        title = "About developers",
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