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
import androidx.navigation.NavHostController
import com.istu.schedule.R
import com.istu.schedule.data.preference.LanguagesPreference
import com.istu.schedule.data.preference.LocalLanguages
import com.istu.schedule.ui.components.base.button.radio.RadioButtonWithText
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15

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
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.application_language),
                isShowBackButton = true,
                onBackPressed = { onBackClick() }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .clip(ShapeTop15)
                    .background(AppTheme.colorScheme.backgroundSecondary)
            ) {
                Column(
                    modifier = Modifier.padding(top = 24.dp, start = 15.dp, end = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    LanguagesPreference.values.map { language ->
                        RadioButtonWithText(
                            modifier = Modifier.padding(5.dp),
                            text = language.toDescription(context),
                            style = AppTheme.typography.subtitle,
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
