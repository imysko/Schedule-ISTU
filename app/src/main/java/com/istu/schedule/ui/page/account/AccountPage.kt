package com.istu.schedule.ui.page.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.data.enums.ProjfairAuthStatus
import com.istu.schedule.util.NavDestinations

@Composable
fun AccountPage(
    bottomNavController: NavController,
    navController: NavController,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val candidate by viewModel.candidate.observeAsState(initial = null)
    val authState by viewModel.authState.observeAsState(ProjfairAuthStatus.UNDEFINED)

    LaunchedEffect(Unit) {
        viewModel.collectSettingsState()
    }

    if (authState == ProjfairAuthStatus.SUCCESS) {
        AuthorizedPage(
            navController = bottomNavController,
            candidate = candidate,
            viewModel = viewModel
        )
    } else {
        LoginPage(
            onLoginPressed = { navController.navigate(NavDestinations.PROJFAIR_LOGIN) }
        )
    }
}
