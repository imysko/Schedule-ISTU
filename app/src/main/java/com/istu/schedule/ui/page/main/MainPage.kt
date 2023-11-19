package com.istu.schedule.ui.page.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.ui.components.navigation.BottomNavBar
import com.istu.schedule.ui.components.navigation.BottomNavGraph
import com.istu.schedule.ui.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainPage(navController: NavHostController) {
    val bottomNavController = rememberNavController()

    Scaffold(
        content = {
            Column {
                BottomNavGraph(
                    navController = navController,
                    bottomNavController = bottomNavController
                )
            }

            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        },
        bottomBar = {
            BottomNavBar(navController = bottomNavController)
        }
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewMainPage() {
    AppTheme {
        MainPage(navController = rememberNavController())
    }
}
