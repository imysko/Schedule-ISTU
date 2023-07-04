package com.istu.schedule.ui.page.settings.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape10

@Composable
fun LanguagePage(
    navController: NavHostController
) {
    LanguagePage(
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
fun LanguagePage(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val languages = LocalLanguages.current
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = AppTheme.colorScheme.background,
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
                    .background(AppTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    LanguagesPreference.values.map { language ->
                        RadioButtonWithText(
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 3.dp)
                                .clip(Shape10),
                            text = language.toDescription(context),
                            style = AppTheme.typography.bodyMedium.copy(
                                fontSize = 16.sp
                            ),
                            selected = language == languages,
                            onSelect = {
                                language.put(context, scope)
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun LanguagePagePreview() {
    AppTheme {
        LanguagePage(onBackClick = { })
    }
}
