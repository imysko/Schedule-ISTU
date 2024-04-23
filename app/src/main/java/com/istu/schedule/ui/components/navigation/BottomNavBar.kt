package com.istu.schedule.ui.components.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.data.preference.LocalLanguages
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop20
import com.istu.schedule.ui.util.NavDestinations

@Composable
fun BottomNavBar(
    navController: NavHostController,
    hasNotifications: Boolean
) {
    LocalLanguages.current

    val bottomNavItems = listOf(
        BottomNavItem.SchedulePage,
        BottomNavItem.NewsfeedPage,
        BottomNavItem.ProjfairPage,
        BottomNavItem.ServicesPage
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = Modifier
            .graphicsLayer {
                shape = ShapeTop20
                clip = true
                shadowElevation = 100f
            },
        containerColor = AppTheme.colorScheme.surface
    ) {
        bottomNavItems.forEach { item ->
            AddItem(
                bottomNavItem = item,
                navBackStackEntry = navBackStackEntry,
                navController = navController,
                hasNotifications = hasNotifications
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.AddItem(
    bottomNavItem: BottomNavItem,
    navBackStackEntry: NavBackStackEntry?,
    navController: NavHostController,
    hasNotifications: Boolean
) {
    val selected =
        bottomNavItem.route == navBackStackEntry?.destination?.route ||
            bottomNavItem.subRouteList.any { route ->
                navBackStackEntry?.destination?.route?.contains(route) == true
            }
    NavigationBarItem(
        modifier = Modifier.navigationBarsPadding(),
        label = {
            Text(
                text = stringResource(id = bottomNavItem.titleResId),
                style = AppTheme.typography.bodySmall.copy(fontSize = 10.sp),
                color = if (selected) {
                    AppTheme.colorScheme.primary
                } else {
                    AppTheme.colorScheme.secondary
                }
            )
        },
        icon = {
            BadgedBox(
                badge = {
                    if (bottomNavItem.hasBadge && hasNotifications) {
                        Badge(containerColor = AppTheme.colorScheme.primary)
                    }
                }
            ) {
                Icon(
                    imageVector = bottomNavItem.icon,
                    contentDescription = "${bottomNavItem.titleResId} Icon"
                )
            }
        },
        selected = selected,
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AppTheme.colorScheme.primary,
            indicatorColor = AppTheme.colorScheme.surface,
            unselectedIconColor = AppTheme.colorScheme.secondary
        ),
        onClick = {
            if (navController.currentDestination?.route != bottomNavItem.route) {
                navController.navigate(bottomNavItem.route) {
                    launchSingleTop = true
                    popUpTo(NavDestinations.MINE_SCHEDULE)
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = false)
fun BottomNavBarPreview() {
    val navController = rememberNavController()
    AppTheme {
        BottomNavBar(
            navController = navController,
            hasNotifications = true
        )
    }
}
