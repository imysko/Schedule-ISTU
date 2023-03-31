package com.istu.schedule.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.istu.schedule.R
import com.istu.schedule.ui.icons.Account
import com.istu.schedule.ui.icons.Book
import com.istu.schedule.ui.icons.Calendar
import com.istu.schedule.ui.icons.Settings

sealed class BottomNavItem(
    val route: String,
    val titleResId: Int,
    val icon: ImageVector,
) {
    object SchedulePage : BottomNavItem(
        route = "schedule",
        titleResId = R.string.schedule,
        icon = Icons.Calendar,
    )

    object ProjfairPage : BottomNavItem(
        route = "projfair",
        titleResId = R.string.projects,
        icon = Icons.Book,
    )

    object SettingsPage : BottomNavItem(
        route = "settings",
        titleResId = R.string.settings,
        icon = Icons.Settings,
    )

    object AccountPage : BottomNavItem(
        route = "account",
        titleResId = R.string.account,
        icon = Icons.Account,
    )
}
