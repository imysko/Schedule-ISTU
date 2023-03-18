package com.istu.schedule.ui.page.settings.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.R
import com.istu.schedule.data.preference.ThemePreference
import com.istu.schedule.data.preference.LocalTheme
import com.istu.schedule.ui.components.base.DisplayText
import com.istu.schedule.ui.components.base.FeedbackIconButton
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.page.settings.SettingItem

@Composable
fun ThemePage(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val theme = LocalTheme.current
    val scope = rememberCoroutineScope()

    SIScaffold(
        navigationIcon = {
            FeedbackIconButton(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = stringResource(R.string.back),
                tint = MaterialTheme.colorScheme.onSurface
            ) {
                navController.popBackStack()
            }
        },
        content = {
            LazyColumn {
                item {
                    DisplayText(text = stringResource(R.string.dark_theme), desc = "")
                }
                item {
                    ThemePreference.values.map {
                        SettingItem(
                            title = it.toDesc(context),
                            onClick = {
                                it.put(context, scope)
                            },
                        ) {
                            RadioButton(selected = it == theme, onClick = {
                                it.put(context, scope)
                            })
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ThemePagePreview() {
    ThemePage(rememberNavController())
}