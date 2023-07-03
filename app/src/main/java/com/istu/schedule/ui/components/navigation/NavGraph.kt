package com.istu.schedule.ui.components.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.istu.schedule.ui.components.ext.animatedComposable
import com.istu.schedule.ui.page.account.login.LoginProjfairPage
import com.istu.schedule.ui.page.main.MainPage
import com.istu.schedule.ui.page.onboarding.OnBoardingPage
import com.istu.schedule.ui.page.settings.binding.BindingPage
import com.istu.schedule.ui.page.settings.language.LanguagePage
import com.istu.schedule.util.NavDestinations

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = NavDestinations.MAIN_PAGE
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        animatedComposable(
            route = NavDestinations.ONBOARDING_PAGE
        ) {
            OnBoardingPage(navController)
        }

        animatedComposable(
            route = NavDestinations.MAIN_PAGE
        ) {
            MainPage(navController)
        }

        animatedComposable(
            route = NavDestinations.BINDING_PAGE
        ) {
            BindingPage(navController)
        }

        animatedComposable(
            route = NavDestinations.LANGUAGE_PAGE
        ) {
            LanguagePage(navController)
        }

        animatedComposable(
            route = NavDestinations.PROJFAIR_LOGIN_PAGE
        ) {
            LoginProjfairPage(navController)
        }
    }
}
