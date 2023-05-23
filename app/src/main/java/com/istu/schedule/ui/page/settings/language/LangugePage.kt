package com.istu.schedule.ui.page.settings.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.istu.schedule.R
import com.istu.schedule.data.preference.LanguagesPreference
import com.istu.schedule.data.preference.LocalLanguages
import com.istu.schedule.ui.components.base.button.radio.RadioButtonWithText
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.ScheduleISTUTheme

@Composable
fun LanguagePage(
    navController: NavHostController,
) {
    LanguagePage(
        onBackClick = { navController.popBackStack() },
    )
}

@Composable
fun LanguagePage(
    onBackClick: () -> Unit,
) {
    val context = LocalContext.current
    val languages = LocalLanguages.current
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.application_language),
                onBackClick = { onBackClick() }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .background(MaterialTheme.colorScheme.background),
            ) {
                Column(
                    modifier = Modifier.padding(top = 24.dp, start = 23.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    LanguagesPreference.values.map {
                        Row {
                            RadioButtonWithText(
                                text = it.toDescription(context),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 16.sp
                                ),
                                selected = it == languages,
                                onSelect = {
                                    it.put(context, scope)
                                }
                            )
                        }
                    }
                }
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun LanguagePagePreview() {
    ScheduleISTUTheme {
        LanguagePage(onBackClick = { })
    }
}