package com.istu.schedule.ui.components.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.istu.schedule.ui.components.ext.animatedComposable
import com.istu.schedule.ui.page.account.AccountPage
import com.istu.schedule.ui.page.account.login.LoginProjfairPage
import com.istu.schedule.ui.page.main.MainPage
import com.istu.schedule.ui.page.projfair.candidate.participations.CandidateParticipationsListPage
import com.istu.schedule.ui.page.projfair.candidate.projects.CandidateProjectsListPage
import com.istu.schedule.ui.page.settings.binding.BindingPage
import com.istu.schedule.ui.page.settings.theme.ThemePage
import com.istu.schedule.util.NavDestinations

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = NavDestinations.MAIN_PAGE
    ) {
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
            route = NavDestinations.PROJFAIR_LOGIN_PAGE
        ) {
            LoginProjfairPage(navController)
        }

        animatedComposable(
            route = NavDestinations.THEME_PAGE
        ) {
            ThemePage(navController)
        }

        animatedComposable(
            route = NavDestinations.ACCOUNT_PAGE
        ) {
            AccountPage(navController)
        }

        animatedComposable(
            route = NavDestinations.CANDIDATE_PROJECTS_PAGE
        ) {
            CandidateProjectsListPage(navController)
        }

        animatedComposable(
            route = NavDestinations.CANDIDATE_PARTICIPATIONS_PAGE
        ) {
            CandidateParticipationsListPage(navController)
        }
    }
}
