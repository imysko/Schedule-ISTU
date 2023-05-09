package com.istu.schedule.ui.components.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.theme.palette.onDark
import com.istu.schedule.util.surfaceColorAtElevation

@Deprecated("Will be removed")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SIScaffold(
    containerColor: Color = MaterialTheme.colorScheme.surface,
    topBarTonalElevation: Dp = 0.dp,
    containerTonalElevation: Dp = 0.dp,
    navigationIcon: (@Composable () -> Unit)? = null,
    title: @Composable () -> Unit = {},
    actions: (@Composable RowScope.() -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    floatingActionButton: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(
                    elevation = topBarTonalElevation,
                    color = containerColor
                )
            ),
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
            elevation = containerTonalElevation,
            color = containerColor
        ) onDark MaterialTheme.colorScheme.surface,
        topBar = {
            if (navigationIcon != null || actions != null) {
                CenterAlignedTopAppBar(
                    title = { title() },
                    navigationIcon = { navigationIcon?.invoke() },
                    actions = { actions?.invoke(this) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            elevation = topBarTonalElevation,
                            color = containerColor
                        ),
                        scrolledContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            elevation = topBarTonalElevation,
                            color = containerColor
                        )
                    )
                )
            }
        },
        content = {
            Column {
                Spacer(modifier = Modifier.height(it.calculateTopPadding()))
                content()
            }
        },
        bottomBar = { bottomBar?.invoke() },
        floatingActionButton = { floatingActionButton?.invoke() }
    )
}
