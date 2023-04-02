package com.istu.schedule.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.istu.schedule.ui.page.main.MainPage
import com.istu.schedule.ui.page.projfair.candidate.CandidatePage
import com.istu.schedule.ui.page.projfair.candidate.participations.CandidateParticipationsListPage
import com.istu.schedule.ui.page.projfair.candidate.projects.CandidateProjectsListPage
import com.istu.schedule.ui.page.settings.binding.BindingPage
import com.istu.schedule.ui.page.settings.projfair.LoginProjfairPage
import com.istu.schedule.ui.page.settings.theme.ThemePage
import com.istu.schedule.util.NavDestinations

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavDestinations.MAIN_PAGE,
    ) {
        composable(
            route = NavDestinations.MAIN_PAGE,
        ) {
            MainPage(navController)
        }

        composable(
            route = NavDestinations.BINDING_PAGE,
        ) {
            BindingPage(navController)
        }

        composable(
            route = NavDestinations.PROJFAIR_LOGIN_PAGE,
        ) {
            LoginProjfairPage(navController)
        }

        composable(
            route = NavDestinations.THEME_PAGE,
        ) {
            ThemePage(navController)
        }

        composable(
            route = NavDestinations.CANDIDATE_PAGE,
        ) {
            CandidatePage(navController)
        }

        composable(
            route = NavDestinations.CANDIDATE_PROJECTS_PAGE,
        ) {
            CandidateProjectsListPage(navController)
        }

        composable(
            route = NavDestinations.CANDIDATE_PARTICIPATIONS_PAGE,
        ) {
            CandidateParticipationsListPage(navController)
        }
    }
}
