package com.istu.schedule.ui.page.settings

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.outlined.Engineering
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Work
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.R
import com.istu.schedule.data.preference.LocalLanguages
import com.istu.schedule.data.preference.LocalTheme
import com.istu.schedule.ui.components.base.DisplayText
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.page.settings.language.LanguageDialog
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue

@Composable
fun SettingsPage(
    navController: NavHostController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settingsUiState = viewModel.settingsUiState.collectAsStateValue()

    val languages = LocalLanguages.current
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val theme = LocalTheme.current

    LaunchedEffect(Unit) {
        viewModel.collectSettingsState()
    }

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
                    SelectableSettingGroupItem(
                        title = settingsUiState.userStatus,
                        description = settingsUiState.userDescription,
                        icon = Icons.Outlined.Groups,
                        onClick = {
                            navController.navigate(NavDestinations.BINDING_SCREEN)
                        }
                    )
                }
                if (!settingsUiState.isProjfairAuthenticated) {
                    item {
                        SelectableSettingGroupItem(
                            title = remember(configuration.locales) {
                                context.resources.getString(R.string.projfair)
                            },
                            description = remember(configuration.locales) {
                                context.resources.getString(R.string.login)
                            },
                            icon = Icons.Outlined.Work,
                            onClick = {
                                navController.navigate(NavDestinations.PROJFAIR_LOGIN_SCREEN)
                            }
                        )
                    }
                }
                else {
                    item {
                        SelectableSettingGroupItem(
                            title = remember(configuration.locales) {
                                context.resources.getString(R.string.projfair)
                            },
                            description = remember(configuration.locales) {
                                settingsUiState.projfairUsername
                            },
                            icon = Icons.Outlined.Work,
                            onClick = {
                                navController.navigate(NavDestinations.CANDIDATE_SCREEN)
                            }
                        )
                    }
                }
                item {
                    val openDialog = remember { mutableStateOf(false)  }

                    if (openDialog.value) {
                        LanguageDialog(
                            isOpenDialog = openDialog
                        )
                    }

                    SelectableSettingGroupItem(
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
                    SelectableSettingGroupItem(
                        title = remember(configuration.locales) {
                            context.resources.getString(R.string.theme)
                        },
                        description = remember(configuration.locales) {
                            theme.toDesc(context)
                        },
                        icon = Icons.Outlined.Palette
                    ) {
                        navController.navigate(NavDestinations.THEME_SCREEN)
                    }
                }
                item {
                    SelectableSettingGroupItem(
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
    SettingsPage(rememberNavController())
}