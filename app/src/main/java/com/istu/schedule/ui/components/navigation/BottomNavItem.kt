package com.istu.schedule.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object SchedulePage: BottomNavItem(
        route = "schedule",
        title = "Schedule",
        icon = Icons.Default.Schedule
    )

    object SearchPage: BottomNavItem(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search
    )

    object ProjectFairPage: BottomNavItem(
        route = "projfair",
        title = "Project Fair",
        icon = Icons.Outlined.Work
    )

    object SettingsPage: BottomNavItem(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}
