package com.istu.schedule.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
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
import com.istu.schedule.ui.page.settings.schedule.SettingSchedulePage
import com.istu.schedule.ui.page.settings.developers.DevelopersPage
import com.istu.schedule.ui.page.settings.language.LanguagePage
import com.istu.schedule.ui.util.NavArguments
import com.istu.schedule.ui.util.NavDestinations

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
            route = "${NavDestinations.FOUND_SCHEDULE}/{${NavArguments.SCHEDULE_TYPE}}/" +
                    "{${NavArguments.SCHEDULE_OWNER_ID}}/{${NavArguments.SCHEDULE_OWNER_TITLE}}",
            arguments = listOf(
                navArgument(NavArguments.SCHEDULE_TYPE) {
                    type = NavType.EnumType(ScheduleType::class.java)
                },
                navArgument(NavArguments.SCHEDULE_OWNER_ID) {
                    type = NavType.IntType
                },
                navArgument(NavArguments.SCHEDULE_OWNER_TITLE) {
                    type = NavType.StringType
                },
            ),
        ) {
            FoundSchedulePage(
                navController = bottomNavController,
                title = it.arguments?.getString(NavArguments.SCHEDULE_OWNER_TITLE),
            )
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
            route = NavDestinations.SETTING_SCHEDULE
        ) {
            SettingSchedulePage(bottomNavController)
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
            route = "${NavDestinations.PROJECT}/{${NavArguments.PROJECT_ID}}/" +
                    "{${NavArguments.CAN_CREATE_PARTICIPATION}}"
        ) {
            it.arguments?.getString(NavArguments.PROJECT_ID)?.toInt()?.let { projectId ->
                it.arguments?.getString(NavArguments.CAN_CREATE_PARTICIPATION)?.toBoolean()
                    ?.let { canCreateParticipation ->
                        ProjectPage(projectId, canCreateParticipation, bottomNavController)
                    }
            }
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = "${NavDestinations.CREATE_PARTICIPATION}/{${NavArguments.PROJECT_ID}}"
        ) {
            it.arguments?.getString(NavArguments.PROJECT_ID)?.toInt()?.let { projectId ->
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
