package com.istu.schedule.ui.page.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.ui.components.navigation.BottomNavBar
import com.istu.schedule.ui.components.navigation.BottomNavGraph

@Composable
fun MainPage(
    navController: NavHostController,
) {
    val bottomNavController = rememberNavController()

    Scaffold(
        content = { paddingValues ->
            Column {
                BottomNavGraph(
                    navController = navController,
                    bottomNavController = bottomNavController,
                )
            }
        },
        bottomBar = {
            BottomNavBar(navController = bottomNavController)
        },
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewMainPage() {
    MainPage(rememberNavController())
}
