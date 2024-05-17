package com.istu.schedule.ui.page.services

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.data.service.notification.Notification
import com.istu.schedule.ui.icons.Book
import com.istu.schedule.ui.icons.Faq
import com.istu.schedule.ui.icons.Settings
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.ui.util.NavDestinations
import com.istu.schedule.util.decreasePadding

@Composable
fun ServicesPage(
    bottomNavController: NavController,
    navController: NavController,
    viewModel: ServicesViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val campusUiState by viewModel.campusUiState.collectAsState()
    val projfairUiState by viewModel.projfairUiState.collectAsState()
    val moodleUiState by viewModel.moodleUiState.collectAsState()
    val notificationList by viewModel.notificationList.collectAsState()

    val moodleAccountIntent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse("https://el.istu.edu/"))
    }

    val campusAccountIntent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse("https://int.istu.edu/"))
    }

    LaunchedEffect(Unit) {
        viewModel.getCampusInfo()
        viewModel.getProjfairInfo()
        // viewModel.getMoodleInfo() // TODO: enable when retrofit is implemented
    }

    ServicesPage(
        campusUiState = campusUiState,
        projfairUiState = projfairUiState,
        moodleUiState = moodleUiState,
        notificationList = notificationList,
        onSettingsClick = { bottomNavController.navigate(NavDestinations.SETTINGS) },
        onMoodleAccountClick = { context.startActivity(moodleAccountIntent) },
        onCampusLoginClick = { navController.navigate(NavDestinations.CAMPUS_LOGIN) },
        onCampusAccountClick = { context.startActivity(campusAccountIntent) },
        onCampusNotificationClick = { bottomNavController.navigate(NavDestinations.NEWSFEED) },
        onProjfairLoginClick = { navController.navigate(NavDestinations.PROJFAIR_LOGIN) },
        onProjfairAccountClick = { bottomNavController.navigate(NavDestinations.ACCOUNT) },
        onProjfairNotificationClick = { bottomNavController.navigate(NavDestinations.PROJFAIR) }
    )
}

@Composable
fun ServicesPage(
    campusUiState: ServicesUiState,
    projfairUiState: ServicesUiState,
    moodleUiState: ServicesUiState,
    notificationList: List<Notification>,
    onSettingsClick: () -> Unit,
    onMoodleAccountClick: () -> Unit,
    onCampusLoginClick: () -> Unit,
    onCampusAccountClick: () -> Unit,
    onCampusNotificationClick: () -> Unit,
    onProjfairLoginClick: () -> Unit,
    onProjfairAccountClick: () -> Unit,
    onProjfairNotificationClick: () -> Unit
) {
    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.services),
                actionButtons = {
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = onSettingsClick,
                        content = {
                            Icon(
                                imageVector = Icons.Settings,
                                contentDescription = stringResource(id = R.string.settings),
                                tint = AppTheme.colorScheme.textSecondary
                            )
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .clip(ShapeTop15)
                .background(AppTheme.colorScheme.backgroundSecondary)
        ) {
            Column(modifier = Modifier.padding(start = 15.dp, top = 20.dp, end = 15.dp)) {
                NotificationSlider(
                    notificationList = notificationList,
                    onCampusClick = onCampusNotificationClick,
                    onProjfairClick = onProjfairNotificationClick,
                    modifier = Modifier
                        .decreasePadding(15.dp)
                        .padding(bottom = if (notificationList.isNotEmpty()) 16.dp else 0.dp)
                )
                ServiceItem(
                    title = stringResource(R.string.moodle),
                    description = when (moodleUiState) {
                        is ServicesUiState.Content -> {
                            if (moodleUiState.data.toInt() != 0) {
                                pluralStringResource(
                                    id = R.plurals.notifications_count,
                                    count = moodleUiState.data.toInt(),
                                    moodleUiState.data.toInt()
                                )
                            } else {
                                stringResource(id = R.string.notifications_count_zero)
                            }
                        }

                        ServicesUiState.Error -> stringResource(R.string.service_message_error)
                        ServicesUiState.Loading -> stringResource(R.string.service_message_loading)
                        ServicesUiState.NetworkUnavailable -> stringResource(
                            R.string.no_internet_connection
                        )

                        ServicesUiState.Unauthorized -> stringResource(R.string.login)
                    },
                    modifier = Modifier.decreasePadding(15.dp)
                )
                HorizontalDivider(color = HalfGray)
                ServiceItem(
                    title = stringResource(R.string.campus),
                    description = when (campusUiState) {
                        is ServicesUiState.Content -> {
                            if (campusUiState.data.toInt() != 0) {
                                pluralStringResource(
                                    id = R.plurals.notifications_count,
                                    count = campusUiState.data.toInt(),
                                    campusUiState.data.toInt()
                                )
                            } else {
                                stringResource(id = R.string.notifications_count_zero)
                            }
                        }

                        ServicesUiState.Error -> stringResource(R.string.service_message_error)
                        ServicesUiState.Loading -> stringResource(R.string.service_message_loading)
                        ServicesUiState.NetworkUnavailable -> stringResource(
                            R.string.no_internet_connection
                        )

                        ServicesUiState.Unauthorized -> stringResource(R.string.login)
                    },
                    onClick = {
                        when (campusUiState) {
                            is ServicesUiState.Content -> onCampusAccountClick()
                            ServicesUiState.Unauthorized -> onCampusLoginClick()
                            else -> {}
                        }
                    },
                    modifier = Modifier.decreasePadding(15.dp)
                )
                HorizontalDivider(color = HalfGray)
                ServiceItem(
                    title = stringResource(R.string.projfair),
                    description = when (projfairUiState) {
                        is ServicesUiState.Content -> projfairUiState.data
                        ServicesUiState.Error -> stringResource(R.string.service_message_error)
                        ServicesUiState.Loading -> stringResource(R.string.service_message_loading)
                        ServicesUiState.NetworkUnavailable -> stringResource(
                            R.string.no_internet_connection
                        )

                        ServicesUiState.Unauthorized -> stringResource(R.string.login)
                    },
                    onClick = {
                        when (projfairUiState) {
                            is ServicesUiState.Content -> onProjfairAccountClick()
                            ServicesUiState.Unauthorized -> onProjfairLoginClick()
                            else -> {}
                        }
                    },
                    modifier = Modifier.decreasePadding(15.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationSlider(
    notificationList: List<Notification>,
    onCampusClick: () -> Unit,
    onProjfairClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { notificationList.size }
    )

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) { page ->
        val notification = notificationList[page]
        val title = when (notification) {
            Notification.Campus -> stringResource(R.string.notification_title_campus)
            Notification.Projfair -> stringResource(R.string.notification_title_projfair)
        }
        val description = when (notification) {
            Notification.Campus -> stringResource(R.string.notification_desc_campus)
            Notification.Projfair -> stringResource(R.string.notification_desc_projfair)
        }
        val icon = when (notification) {
            Notification.Campus -> Icons.Faq
            Notification.Projfair -> Icons.Book
        }
        val onClick = when (notification) {
            Notification.Campus -> onCampusClick
            Notification.Projfair -> onProjfairClick
        }

        NotificationItem(
            title = title,
            description = description,
            icon = icon,
            onClick = onClick,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
    }
}

@Preview
@Composable
fun PreviewServicesPage() {
    AppTheme {
        ServicesPage(
            campusUiState = ServicesUiState.Content("10"),
            moodleUiState = ServicesUiState.Content("5"),
            projfairUiState = ServicesUiState.Content("Иванов Иван Иванович"),
            notificationList = listOf(Notification.Campus, Notification.Projfair),
            onSettingsClick = {},
            onMoodleAccountClick = {},
            onCampusLoginClick = {},
            onCampusAccountClick = {},
            onCampusNotificationClick = {},
            onProjfairLoginClick = {},
            onProjfairAccountClick = {},
            onProjfairNotificationClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewNotificationSlider() {
    AppTheme {
        NotificationSlider(
            notificationList = listOf(
                Notification.Projfair,
                Notification.Campus
            ),
            onCampusClick = {},
            onProjfairClick = {}
        )
    }
}
