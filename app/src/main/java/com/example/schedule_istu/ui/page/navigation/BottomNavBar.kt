package com.example.schedule_istu.ui.page.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavBar(navController: NavHostController) {
    val bottomNavItems = listOf(
        BottomNavItem.SchedulePage,
        BottomNavItem.SearchPage,
        BottomNavItem.ProjectFairPage,
        BottomNavItem.SettingsPage
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {
        Spacer(modifier = Modifier.width(16.dp))
        bottomNavItems.forEach { item ->
            AddItem(
                bottomNavItem = item,
                navBackStackEntry = navBackStackEntry,
                navController = navController
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun RowScope.AddItem(
    bottomNavItem: BottomNavItem,
    navBackStackEntry: NavBackStackEntry?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(
                text = bottomNavItem.title,
                fontWeight = FontWeight.SemiBold
            )
        },
        icon = {
            Icon(
                imageVector = bottomNavItem.icon,
                contentDescription = "${bottomNavItem.title} Icon"
            )
        },
        selected = bottomNavItem.route == navBackStackEntry?.destination?.route,
        alwaysShowLabel = false,
        onClick = { navController.navigate(bottomNavItem.route) }
    )
}

@Composable
@Preview(showBackground = true)
fun BottomNavBarPreview() {
    val navController = rememberNavController()
    BottomNavBar(navController = navController)
}