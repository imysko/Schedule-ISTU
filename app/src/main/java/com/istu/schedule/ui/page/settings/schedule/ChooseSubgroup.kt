package com.istu.schedule.ui.page.settings.schedule

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.entities.Subgroup
import com.istu.schedule.ui.components.base.button.radio.RadioButtonWithText
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15

@Composable
fun ChooseSubgroup(
    selectedSubgroup: Subgroup,
    onBackClick: () -> Unit,
    onChooseSubgroup: (Subgroup) -> Unit
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
                Column(
                    modifier = Modifier.padding(top = 19.dp, start = 15.dp, end = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.choose_subgroup),
                        style = AppTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = AppTheme.colorScheme.secondary
                        )
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Subgroup.values().map { subgroup ->
                            RadioButtonWithText(
                                modifier = Modifier.padding(5.dp),
                                text = when (subgroup) {
                                    Subgroup.ALL -> stringResource(id = R.string.all_subgroup)
                                    Subgroup.FIRST -> stringResource(id = R.string.first_subgroup)
                                    Subgroup.SECOND -> stringResource(id = R.string.second_subgroup)
                                },
                                style = AppTheme.typography.subtitle,
                                selected = subgroup == selectedSubgroup,
                                onSelect = { onChooseSubgroup(subgroup) }
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.windowInsetsBottomHeight(
                        WindowInsets.navigationBars
                    )
                )
            }
        }
    )
}

@Preview(showBackground = true, name = "Displayed subgroup list", locale = "ru")
@Composable
fun ChooseSubgroupPreview() {
    ChooseSubgroup(
        selectedSubgroup = Subgroup.SECOND,
        onBackClick = { },
        onChooseSubgroup = { }
    )
}
