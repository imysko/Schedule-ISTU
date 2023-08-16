package com.istu.schedule.ui.page.settings.binding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
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
import com.istu.schedule.domain.model.schedule.Institute
import com.istu.schedule.ui.icons.Forward
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.ShapeTop15

@Composable
fun ChooseInstitute(
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
                title = stringResource(id = R.string.account),
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
                Column(
                    modifier = Modifier
                        .padding(top = 19.dp, start = 15.dp, end = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.choose_institute),
                        style = AppTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = AppTheme.colorScheme.secondary
                        )
                    )

                    LazyColumn(
                        modifier = Modifier.padding(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
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
                                            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
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

                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp)
                                        .height(2.dp),
                                    color = HalfGray
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
@Preview(showBackground = true, locale = "ru")
fun ChooseInstitutePreview() {
    AppTheme {
        ChooseInstitute(
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
