package com.istu.schedule.ui.components.navigation

import androidx.compose.foundation.background
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
            }
            .navigationBarsPadding(),
        containerColor = MaterialTheme.colorScheme.background,
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
    NavigationBarItem(
        label = {
            Text(
                text = stringResource(id = bottomNavItem.titleResId),
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
            )
        },
        icon = {
            Icon(
                imageVector = bottomNavItem.icon,
                contentDescription = "${bottomNavItem.titleResId} Icon",
            )
        },
        selected = bottomNavItem.route == navBackStackEntry?.destination?.route,
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            indicatorColor = MaterialTheme.colorScheme.background,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            unselectedTextColor = MaterialTheme.colorScheme.secondary,
        ),
        onClick = { navController.navigate(bottomNavItem.route) },
    )
}

@Composable
@Preview(showBackground = false)
fun BottomNavBarPreview() {
    val navController = rememberNavController()
    BottomNavBar(navController = navController)
}
