package com.istu.schedule.ui.page.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.SettingsItem
import com.istu.schedule.ui.icons.Account
import com.istu.schedule.ui.icons.People
import com.istu.schedule.ui.icons.Star
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations

@Composable
fun SettingsPage(navController: NavHostController) {
    SettingsPage(
        onBindingClick = { navController.navigate(NavDestinations.BINDING_PAGE) },
        onFavoritesListClick = { },
        onLanguageClick = { navController.navigate(NavDestinations.LANGUAGE_PAGE) },
        onAboutClick = { },
    )
}

@Composable
fun SettingsPage(
    onBindingClick: () -> Unit,
    onFavoritesListClick: () -> Unit,
    onLanguageClick: () -> Unit,
    onAboutClick: () -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            Column(modifier = Modifier
                .statusBarsPadding()
                .padding(15.dp)) {
                Text(
                    text = stringResource(id = R.string.settings),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .clip(ShapeTop15)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                item {
                    SettingsItem(
                        title = stringResource(id = R.string.account),
                        icon = Icons.Account,
                        onClick = {
                            onBindingClick()
                        }
                    )
                }
                item {
                    SettingsItem(
                        title = stringResource(id = R.string.favorites_list),
                        icon = Icons.Star,
                        onClick = { onFavoritesListClick() }
                    )
                }
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
    ScheduleISTUTheme {
        SettingsPage(
            onBindingClick = {  },
            onFavoritesListClick = { },
            onLanguageClick = {  },
            onAboutClick = { },
        )
    }
}
