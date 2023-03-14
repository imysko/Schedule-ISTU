package com.istu.schedule.ui.page.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.components.navigation.BottomNavBar
import com.istu.schedule.ui.components.navigation.BottomNavGraph

@Composable
fun MainPage() {
    val navController = rememberNavController()
    SIScaffold(
        content = {
            BottomNavGraph(navController = navController)
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewMainPage() {
    MainPage()
}