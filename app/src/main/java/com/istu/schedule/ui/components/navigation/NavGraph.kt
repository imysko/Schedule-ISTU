package com.istu.schedule.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.istu.schedule.ui.page.main.MainPage
import com.istu.schedule.ui.page.binding.BindingPage
import com.istu.schedule.ui.page.settings.projfair.LoginProjfairPage
import com.istu.schedule.util.NavDestinations

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavDestinations.MAIN_SCREEN
    ) {
        composable(
            route = NavDestinations.MAIN_SCREEN
        ) {
            MainPage(navController)
        }

        composable(
            route = NavDestinations.BINDING_SCREEN
        ) {
            BindingPage()
        }

        composable(
            route = NavDestinations.PROJFAIR_LOGIN_SCREEN
        ) {
            LoginProjfairPage(navController)
        }
    }
}