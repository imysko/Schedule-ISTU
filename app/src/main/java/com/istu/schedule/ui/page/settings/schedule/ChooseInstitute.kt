package com.istu.schedule.ui.page.settings.schedule

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.entities.schedule.Institute
import com.istu.schedule.ui.components.base.LoadingPanel
import com.istu.schedule.ui.components.base.SIAnimatedVisibilityFadeOnly
import com.istu.schedule.ui.icons.Forward
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.ShapeTop15

@Composable
fun ChooseInstitute(
    isLoading: Boolean,
    institutesList: List<Institute> = emptyList(),
    onBackClick: () -> Unit,
    onChooseInstitute: (chosenInstitute: Institute) -> Unit
) {
    BackHandler {
        onBackClick()
    }

    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.setting_schedule),
                isShowBackButton = true,
                onBackClick = { onBackClick() }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .clip(ShapeTop15)
                    .background(AppTheme.colorScheme.backgroundSecondary)
            ) {
                Box {
                    SIAnimatedVisibilityFadeOnly(isLoading) {
                        LoadingPanel(isLoading)
                    }
                    SIAnimatedVisibilityFadeOnly(!isLoading) {
                        LazyColumn(
                            modifier = Modifier.padding(horizontal = 15.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(top = 20.dp, bottom = 30.dp)
                        ) {
                            item {
                                Text(
                                    modifier = Modifier.padding(bottom = 10.dp),
                                    text = stringResource(id = R.string.choose_institute),
                                    style = AppTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = AppTheme.colorScheme.secondary
                                    )
                                )
                            }
                            institutesList.forEach {
                                item {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(Shape10)
                                            .clickable { onChooseInstitute(it) }
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(
                                                    start = 10.dp,
                                                    top = 10.dp,
                                                    bottom = 10.dp
                                                ),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                modifier = Modifier.weight(0.7f),
                                                text = it.instituteTitle!!,
                                                style = AppTheme.typography.bodyMedium.copy(
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                            )

                                            Icon(
                                                modifier = Modifier.size(17.dp),
                                                imageVector = Icons.Forward,
                                                contentDescription = stringResource(
                                                    id = R.string.forward
                                                )
                                            )
                                        }
                                    }

                                    HorizontalDivider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 10.dp)
                                            .height(2.dp),
                                        color = HalfGray
                                    )
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.height(64.dp))
                                Spacer(
                                    modifier = Modifier.windowInsetsBottomHeight(
                                        WindowInsets.navigationBars
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
@Preview(
    showBackground = true,
    name = "Displayed institute list",
    group = "Choose institute",
    locale = "ru"
)
fun ChooseInstitutePreview() {
    AppTheme {
        ChooseInstitute(
            isLoading = false,
            institutesList = mutableListOf(
                Institute(
                    instituteId = 0,
                    instituteTitle = "Институт архитектуры, строительства и дизайна",
                    courses = emptyList()
                ),
                Institute(
                    instituteId = 0,
                    instituteTitle = "Институт Информационных Технологий и Анализа Данных",
                    courses = emptyList()
                )
            ),
            onBackClick = { },
            onChooseInstitute = { }
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Is loading", group = "Choose institute", locale = "ru")
fun ChooseInstituteIsLoadingPreview() {
    AppTheme {
        ChooseInstitute(
            isLoading = true,
            institutesList = emptyList(),
            onBackClick = { },
            onChooseInstitute = { }
        )
    }
}
