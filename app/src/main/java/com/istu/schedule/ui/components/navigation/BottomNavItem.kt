package com.istu.schedule.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.istu.schedule.R
import com.istu.schedule.ui.icons.Account
import com.istu.schedule.ui.icons.Book
import com.istu.schedule.ui.icons.Calendar
import com.istu.schedule.ui.icons.Faq
import com.istu.schedule.ui.icons.Settings
import com.istu.schedule.ui.util.NavDestinations

sealed class BottomNavItem(
    val route: String,
    val titleResId: Int,
    val icon: ImageVector,
    val subRouteList: List<String> = listOf()
) {
    data object SchedulePage : BottomNavItem(
        route = NavDestinations.MINE_SCHEDULE,
        titleResId = R.string.schedule,
        icon = Icons.Calendar,
        subRouteList = listOf(
            NavDestinations.SEARCH_SCHEDULE,
            NavDestinations.FOUND_SCHEDULE
        )
    )

    data object NewsfeedPage : BottomNavItem(
        route = NavDestinations.NEWSFEED,
        titleResId = R.string.newsfeed,
        icon = Icons.Faq
    )

    data object ProjfairPage : BottomNavItem(
        route = NavDestinations.PROJFAIR,
        titleResId = R.string.projects,
        icon = Icons.Book,
        subRouteList = listOf(
            NavDestinations.FILTERS,
            NavDestinations.PROJECT,
            NavDestinations.CREATE_PARTICIPATION
        )
    )

    data object SettingsPage : BottomNavItem(
        route = NavDestinations.SETTINGS,
        titleResId = R.string.settings,
        icon = Icons.Settings,
        subRouteList = listOf(
            NavDestinations.SETTING_SCHEDULE,
            NavDestinations.LANGUAGE,
            NavDestinations.DEVELOPERS
        )
    )

    data object AccountPage : BottomNavItem(
        route = NavDestinations.ACCOUNT,
        titleResId = R.string.account,
        icon = Icons.Account
    )
}
