package com.istu.schedule.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector
import com.istu.schedule.R

sealed class BottomNavItem(
    val route: String,
    val titleResid: Int,
    val icon: ImageVector
) {
    object SchedulePage: BottomNavItem(
        route = "schedule",
        titleResid = R.string.schedule,
        icon = Icons.Default.Schedule
    )

    object SearchPage: BottomNavItem(
        route = "search",
        titleResid = R.string.search,
        icon = Icons.Default.Search
    )

    object ProjectFairPage: BottomNavItem(
        route = "projfair",
        titleResid = R.string.projfair,
        icon = Icons.Outlined.Work
    )

    object SettingsPage: BottomNavItem(
        route = "settings",
        titleResid = R.string.settings,
        icon = Icons.Default.Settings
    )
}
