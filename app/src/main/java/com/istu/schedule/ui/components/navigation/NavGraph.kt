package com.istu.schedule.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.istu.schedule.ui.components.ext.animatedComposable
import com.istu.schedule.ui.page.account.login.LoginProjfairPage
import com.istu.schedule.ui.page.main.MainPage
import com.istu.schedule.ui.page.newsfeed.login.LoginCampusPage
import com.istu.schedule.ui.page.onboarding.OnBoardingPage
import com.istu.schedule.ui.page.settings.schedule.SettingSchedulePage
import com.istu.schedule.ui.util.NavDestinations
import com.istu.schedule.util.isFirstLaunch

@Composable
fun NavGraph(
    navController: NavHostController
) {
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()

    NavHost(
        navController = navController,
        startDestination = if (context.isFirstLaunch) {
            NavDestinations.ONBOARDING
        } else {
            NavDestinations.MAIN
        }
    ) {
        animatedComposable(
            route = NavDestinations.ONBOARDING
        ) {
            OnBoardingPage(navController)
            SetStatusBarIconColor(systemUiController, true)
        }

        animatedComposable(
            route = NavDestinations.MAIN
        ) {
            MainPage(navController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = NavDestinations.SETTING_SCHEDULE
        ) {
            SettingSchedulePage(navController)
            SetStatusBarIconColor(systemUiController)
        }

        animatedComposable(
            route = NavDestinations.PROJFAIR_LOGIN
        ) {
            LoginProjfairPage(navController)
            SetStatusBarIconColor(systemUiController, true)
        }

        animatedComposable(
            route = NavDestinations.CAMPUS_LOGIN
        ) {
            LoginCampusPage(navController)
            SetStatusBarIconColor(systemUiController, true)
        }
    }
}

@Composable
private fun SetStatusBarIconColor(systemUiController: SystemUiController, isDark: Boolean = false) {
    SideEffect { systemUiController.setStatusBarColor(Color.Transparent, isDark) }
}
