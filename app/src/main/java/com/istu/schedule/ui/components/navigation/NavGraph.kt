package com.istu.schedule.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.istu.schedule.ui.page.main.MainPage
import com.istu.schedule.ui.page.settings.binding.BindingPage
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
    }
}