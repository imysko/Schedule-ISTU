package com.istu.schedule.ui.components.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.istu.schedule.ui.components.ext.animatedComposable
import com.istu.schedule.ui.page.account.AccountPage
import com.istu.schedule.ui.page.projfair.list.ProjectsListPage
import com.istu.schedule.ui.page.projfair.list.filter.FiltersPage
import com.istu.schedule.ui.page.projfair.project.ProjectPage
import com.istu.schedule.ui.page.schedule.SchedulePage
import com.istu.schedule.ui.page.settings.SettingsPage
import com.istu.schedule.util.NavDestinations

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    bottomNavController: NavHostController
) {
    val systemUiController = rememberSystemUiController()

    AnimatedNavHost(
        navController = bottomNavController,
        startDestination = BottomNavItem.SchedulePage.route
    ) {
        animatedComposable(
            route = BottomNavItem.SchedulePage.route
        ) {
            SchedulePage(navController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = BottomNavItem.ProjfairPage.route
        ) {
            ProjectsListPage(bottomNavController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = BottomNavItem.SettingsPage.route
        ) {
            SettingsPage(navController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = BottomNavItem.AccountPage.route
        ) {
            AccountPage(bottomNavController, navController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = NavDestinations.FILTERS_PAGE
        ) {
            FiltersPage(bottomNavController)
            SetStatusBarIconColor(systemUiController, true)
        }

        animatedComposable(
            route = "${NavDestinations.PROJECT_PAGE}/{projectId}"
        ) {
            it.arguments?.getString("projectId")?.toInt()?.let { projectId ->
                ProjectPage(projectId, bottomNavController)
            }
            SetStatusBarIconColor(systemUiController)
        }
    }
}

@Composable
private fun SetStatusBarIconColor(systemUiController: SystemUiController, isDark: Boolean = false) {
    SideEffect { systemUiController.setStatusBarColor(Color.Transparent, isDark) }
}
