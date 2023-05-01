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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            Column(modifier = Modifier.statusBarsPadding().padding(15.dp)) {
                Text(
                    text = remember(configuration.locales) {
                        context.resources.getString(R.string.settings)
                    },
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
                        title = remember(configuration.locales) {
                            context.resources.getString(R.string.account)
                        },
                        icon = Icons.Account,
                        onClick = {
                            navController.navigate(NavDestinations.BINDING_PAGE)
                        }
                    )
                }
                item {
                    SettingsItem(
                        title = remember(configuration.locales) {
                            context.resources.getString(R.string.favorites_list)
                        },
                        icon = Icons.Star,
                        onClick = {}
                    )
                }
                item {
                    SettingsItem(
                        title = remember(configuration.locales) {
                            context.resources.getString(R.string.application_language)
                        },
                        icon = Icons.Account,
                        onClick = {}
                    )
                }
                item {
                    SettingsItem(
                        title = remember(configuration.locales) {
                            context.resources.getString(R.string.about_the_developers)
                        },
                        icon = Icons.People,
                        onClick = {}
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
        SettingsPage(rememberNavController())
    }
}
