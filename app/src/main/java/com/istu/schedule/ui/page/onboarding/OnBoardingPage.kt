package com.istu.schedule.ui.page.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.FilledButton
import com.istu.schedule.ui.components.base.OutlineButton
import com.istu.schedule.ui.theme.GrayDisabled
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.util.collectAsStateValue

@Composable
fun OnBoardingPage(
    viewModel: OnBoardingViewModel = hiltViewModel(),
) {
    val onBoardingUiState = viewModel.onBoardingUiState.collectAsStateValue()

    Surface(
        color = MaterialTheme.colorScheme.background,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                ) {
                    when (onBoardingUiState.pageNumber) {
                        1 -> {
                            OnBoardingContent(
                                painterResource = painterResource(id = R.drawable.look_schedule),
                                title = stringResource(id = R.string.look_schedule),
                                description = stringResource(id = R.string.look_schedule_description),
                            )
                        }
                        2 -> {
                            OnBoardingContent(
                                painterResource = painterResource(id = R.drawable.choose_project),
                                title = stringResource(id = R.string.choose_project),
                                description = stringResource(id = R.string.choose_project_description),
                            )
                        }
                        3 -> {
                            OnBoardingContent(
                                painterResource = painterResource(id = R.drawable.adjust_to_you),
                                title = stringResource(id = R.string.adjust_to_you),
                                description = stringResource(id = R.string.adjust_to_you_description),
                            )
                        }
                        5 -> {
                            OnBoardingContent(
                                painterResource = painterResource(id = R.drawable.login_to_personal_account),
                                title = stringResource(id = R.string.login_to_personal_account),
                                description = stringResource(id = R.string.login_to_personal_account_description),
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.padding(bottom = 40.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                        ) {
                            for (i in 1..5) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (onBoardingUiState.pageNumber == i)
                                                MaterialTheme.colorScheme.primary else GrayDisabled
                                        ),
                                )
                            }
                        }

                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            if (onBoardingUiState.isShowNextButton) {
                                FilledButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp),
                                    text = stringResource(id = R.string.next_step),
                                    onClick = { viewModel.onNextButtonClick() },
                                )
                            }

                            if (onBoardingUiState.isShowSkipSetupScheduleButton) {
                                OutlineButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp),
                                    text = stringResource(id = R.string.skip_setting),
                                    onClick = { viewModel.onSkipSetupScheduleButtonClick() },
                                )
                            }

                            if (onBoardingUiState.isShowSetupScheduleButton) {
                                FilledButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp),
                                    text = stringResource(id = R.string.setup_schedule),
                                    onClick = { viewModel.onSetupScheduleButtonClick() },
                                )
                            }

                            if (onBoardingUiState.isShowSkipAuthorizationButton) {
                                OutlineButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp),
                                    text = stringResource(id = R.string.skip),
                                    onClick = { viewModel.onSkipAuthorizationButtonClick() },
                                )
                            }

                            if (onBoardingUiState.isShowAuthorizationButton) {
                                FilledButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp),
                                    text = stringResource(id = R.string.login_campus),
                                    onClick = { viewModel.onAuthorizationButtonClick() },
                                )
                            }
                        }
                    }
                }
            }
        },
    )
}

@Composable
@Preview(showBackground = true, locale = "ru")
fun OnBoardingPagePreview() {
    ScheduleISTUTheme {
        OnBoardingPage()
    }
}