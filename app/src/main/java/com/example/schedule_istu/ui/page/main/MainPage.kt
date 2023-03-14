package com.example.schedule_istu.ui.page.main

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.schedule_istu.ui.page.navigation.BottomNavBar
import com.example.schedule_istu.ui.page.navigation.BottomNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun MainPage() {
    val navController = rememberNavController()
    Scaffold(
        content = {
            BottomNavGraph(navController = navController)
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
}