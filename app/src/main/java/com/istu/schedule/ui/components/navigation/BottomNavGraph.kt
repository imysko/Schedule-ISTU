package com.istu.schedule.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.istu.schedule.ui.page.projfair.list.ListPage
import com.istu.schedule.ui.page.schedule.SchedulePage
import com.istu.schedule.ui.page.search.SearchPage
import com.istu.schedule.ui.page.settings.SettingsPage

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    bottomNavController: NavHostController
) {
    NavHost(
        navController = bottomNavController,
        startDestination = BottomNavItem.SchedulePage.route
    ) {
        composable(
            route = BottomNavItem.SchedulePage.route
        ) {
            SchedulePage()
        }

        composable(
            route = BottomNavItem.SearchPage.route
        ) {
            SearchPage()
        }

        composable(
            route = BottomNavItem.ProjectFairPage.route
        ) {
            ListPage()
        }

        composable(
            route = BottomNavItem.SettingsPage.route
        ) {
            SettingsPage(navController)
        }
    }
}