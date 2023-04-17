package com.istu.schedule.ui.components.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.ui.theme.ShapeTop20

@Composable
fun BottomNavBar(navController: NavHostController) {
    val bottomNavItems = listOf(
        BottomNavItem.SchedulePage,
        BottomNavItem.ProjfairPage,
        BottomNavItem.SettingsPage,
        BottomNavItem.AccountPage,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = Modifier
            .graphicsLayer {
                shape = ShapeTop20
                clip = true
                shadowElevation = 100f
            }
            .navigationBarsPadding(),
        containerColor = MaterialTheme.colorScheme.onSurface,
    ) {
        bottomNavItems.forEach { item ->
            AddItem(
                bottomNavItem = item,
                navBackStackEntry = navBackStackEntry,
                navController = navController,
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    bottomNavItem: BottomNavItem,
    navBackStackEntry: NavBackStackEntry?,
    navController: NavHostController,
) {
    val selected = bottomNavItem.route == navBackStackEntry?.destination?.route
    NavigationBarItem(
        label = {
            Text(
                text = stringResource(id = bottomNavItem.titleResId),
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            )
        },
        icon = {
            Icon(
                imageVector = bottomNavItem.icon,
                contentDescription = "${bottomNavItem.titleResId} Icon",
            )
        },
        selected = selected,
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            indicatorColor = MaterialTheme.colorScheme.onSurface,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
        ),
        onClick = { navController.navigate(bottomNavItem.route) },
    )
}

@Composable
@Preview(showBackground = false)
fun BottomNavBarPreview() {
    val navController = rememberNavController()
    ScheduleISTUTheme {
        BottomNavBar(navController = navController)
    }
}
