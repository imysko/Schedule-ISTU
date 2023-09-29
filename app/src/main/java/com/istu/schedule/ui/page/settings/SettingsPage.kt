package com.istu.schedule.ui.page.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.SettingsItem
import com.istu.schedule.ui.icons.Account
import com.istu.schedule.ui.icons.Calendar
import com.istu.schedule.ui.icons.People
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations

@Composable
fun SettingsPage(navController: NavHostController) {
    SettingsPage(
        onBindingClick = { navController.navigate(NavDestinations.BINDING) },
        onLanguageClick = { navController.navigate(NavDestinations.LANGUAGE) }
    ) { navController.navigate(NavDestinations.DEVELOPERS) }
}

@Composable
fun SettingsPage(
    onBindingClick: () -> Unit,
    onLanguageClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = { TopBar(title = stringResource(id = R.string.settings)) },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .clip(ShapeTop15)
                    .background(AppTheme.colorScheme.backgroundSecondary)
            ) {
                item {
                    SettingsItem(
                        title = stringResource(id = R.string.schedule),
                        icon = Icons.Calendar,
                        onClick = {
                            onBindingClick()
                        }
                    )
                }
                // TODO: return when favourite list page will be created
                // item {
                //     SettingsItem(
                //         title = stringResource(id = R.string.favorites_list),
                //         icon = Icons.Star,
                //         onClick = { onFavoritesListClick() }
                //     )
                // }
                item {
                    SettingsItem(
                        title = stringResource(id = R.string.application_language),
                        icon = Icons.Account,
                        onClick = { onLanguageClick() }
                    )
                }
                item {
                    SettingsItem(
                        title = stringResource(id = R.string.about_the_developers),
                        icon = Icons.People,
                        onClick = { onAboutClick() }
                    )
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun SettingsPagePreview() {
    AppTheme {
        SettingsPage(
            onBindingClick = { },
            onLanguageClick = { }
        ) { }
    }
}
