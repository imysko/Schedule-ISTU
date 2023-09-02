package com.istu.schedule.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.istu.schedule.data.enums.ScheduleType
import com.istu.schedule.ui.components.ext.animatedComposable
import com.istu.schedule.ui.page.account.AccountPage
import com.istu.schedule.ui.page.projfair.list.ProjectsListPage
import com.istu.schedule.ui.page.projfair.list.filter.FiltersPage
import com.istu.schedule.ui.page.projfair.participation.CreateParticipationPage
import com.istu.schedule.ui.page.projfair.project.ProjectPage
import com.istu.schedule.ui.page.schedule.found.FoundSchedulePage
import com.istu.schedule.ui.page.schedule.mine.MineSchedulePage
import com.istu.schedule.ui.page.schedule.search.SearchSchedulePage
import com.istu.schedule.ui.page.settings.SettingsPage
import com.istu.schedule.ui.page.settings.binding.BindingPage
import com.istu.schedule.ui.page.settings.developers.DevelopersPage
import com.istu.schedule.ui.page.settings.language.LanguagePage
import com.istu.schedule.util.NavDestinations

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    bottomNavController: NavHostController
) {
    val systemUiController = rememberSystemUiController()

    NavHost(
        navController = bottomNavController,
        startDestination = BottomNavItem.SchedulePage.route
    ) {
        animatedComposable(
            route = BottomNavItem.SchedulePage.route
        ) {
            MineSchedulePage(bottomNavController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = NavDestinations.SEARCH_SCHEDULE
        ) {
            SearchSchedulePage(bottomNavController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = "${NavDestinations.FOUND_SCHEDULE}/{scheduleType}/{id}"
        ) {
            ScheduleType.valueOf(it.arguments?.getString("scheduleType")!!).let { scheduleType ->
                it.arguments?.getString("id")?.toInt()
                    ?.let { id ->
                        FoundSchedulePage(scheduleType, id, bottomNavController)
                    }
            }
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
            SettingsPage(bottomNavController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = BottomNavItem.AccountPage.route
        ) {
            AccountPage(bottomNavController, navController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = NavDestinations.BINDING
        ) {
            BindingPage(bottomNavController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = NavDestinations.LANGUAGE
        ) {
            LanguagePage(bottomNavController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = NavDestinations.DEVELOPERS
        ) {
            DevelopersPage(bottomNavController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = NavDestinations.FILTERS
        ) {
            FiltersPage(bottomNavController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = "${NavDestinations.PROJECT}/{projectId}/{canCreateParticipation}"
        ) {
            it.arguments?.getString("projectId")?.toInt()?.let { projectId ->
                it.arguments?.getString("canCreateParticipation")?.toBoolean()
                    ?.let { canCreateParticipation ->
                        ProjectPage(projectId, canCreateParticipation, bottomNavController)
                    }
            }
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = "${NavDestinations.CREATE_PARTICIPATION}/{projectId}"
        ) {
            it.arguments?.getString("projectId")?.toInt()?.let { projectId ->
                CreateParticipationPage(projectId, bottomNavController)
            }
            SetStatusBarIconColor(systemUiController)
        }
    }
}

@Composable
private fun SetStatusBarIconColor(systemUiController: SystemUiController, isDark: Boolean = false) {
    SideEffect { systemUiController.setStatusBarColor(Color.Transparent, isDark) }
}
