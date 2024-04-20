package com.istu.schedule.ui.page.newsfeed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.BigPlaceholder
import com.istu.schedule.ui.components.base.NoInternetPanel
import com.istu.schedule.ui.components.base.SIAnimatedVisibilityFadeOnly
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.ui.util.NavDestinations

@Composable
fun NewsfeedPage(
    navController: NavController,
    viewModel: NewsfeedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    NewsfeedPage(
        uiState = uiState,
        onCreate = viewModel::getBlogPostList,
        onLoginClick = { navController.navigate(NavDestinations.CAMPUS_LOGIN) }
    )
}

@Composable
fun NewsfeedPage(
    uiState: NewsfeedUiState,
    onCreate: () -> Unit,
    onLoginClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        onCreate()
    }

    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = { TopBar(title = stringResource(id = R.string.newsfeed)) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .clip(ShapeTop15)
                .background(AppTheme.colorScheme.backgroundSecondary)
        ) {
            SIAnimatedVisibilityFadeOnly(visible = uiState is NewsfeedUiState.Loading) {
                ListPlaceHolders()
            }
            SIAnimatedVisibilityFadeOnly(visible = uiState is NewsfeedUiState.Content) {
                BlogPostList((uiState as NewsfeedUiState.Content).blogPostList)
            }
            SIAnimatedVisibilityFadeOnly(visible = uiState is NewsfeedUiState.Unauthorized) {
                LoginPage(onLoginClick = onLoginClick)
            }
            SIAnimatedVisibilityFadeOnly(visible = uiState is NewsfeedUiState.Error) {
            }
            SIAnimatedVisibilityFadeOnly(visible = uiState is NewsfeedUiState.NetworkUnavailable) {
                NoInternetPanel(onReloadClick = onCreate)
            }
        }
    }
}

@Composable
private fun ListPlaceHolders() {
    Column(
        modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        BigPlaceholder()
        BigPlaceholder()
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun PreviewNewsfeedPage() {
    AppTheme {
        NewsfeedPage(
            uiState = NewsfeedUiState.Loading,
            onCreate = { },
            onLoginClick = {}
        )
    }
}
