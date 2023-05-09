package com.istu.schedule.ui.page.settings.language

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.istu.schedule.ui.icons.Back
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
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(15.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier.clickable { onBackClick() },
                    ) {
                        Icon(
                            modifier = Modifier.size(22.dp),
                            imageVector = Icons.Back,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }

                    Text(
                        text = stringResource(id = R.string.application_language),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
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