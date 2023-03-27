package com.istu.schedule.ui.page.projfair.candidate

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.DisplayText
import com.istu.schedule.ui.components.base.FeedbackIconButton
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.page.settings.SettingItem
import com.istu.schedule.util.NavDestinations

@Composable
fun CandidatePage(
    navController: NavController,
    viewModel: CandidateViewModel = hiltViewModel(),
) {
    val candidate by viewModel.candidate.observeAsState(initial = null)

    AppComposable(
        viewModel = viewModel,
        content = {
            CandidatePage(
                navController = navController,
                candidate = candidate,
                onLogoutClick = { viewModel.logout() },
            )
        },
    )
}

@Composable
fun CandidatePage(
    navController: NavController,
    candidate: Candidate?,
    onLogoutClick: () -> Unit = {},
) {
    SIScaffold(
        navigationIcon = {
            FeedbackIconButton(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                onClick = { navController.popBackStack() },
            )
        },
    ) {
        LazyColumn {
            item {
                DisplayText(
                    text = stringResource(R.string.projfair),
                    desc = candidate?.fio ?: "",
                )
            }
            item {
                ListItem(
                    headlineContent = { Text(stringResource(R.string.email)) },
                    supportingContent = { Text(candidate?.email ?: "") },
                    leadingContent = {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = stringResource(R.string.email),
                        )
                    },
                )
            }
            item {
                var phone = candidate?.phone ?: ""
                if (phone.isBlank()) {
                    phone = stringResource(R.string.not_specified)
                }
                ListItem(
                    headlineContent = { Text(stringResource(R.string.phone_number)) },
                    supportingContent = { Text(phone) },
                    leadingContent = {
                        Icon(
                            Icons.Filled.Phone,
                            contentDescription = stringResource(R.string.phone_number),
                        )
                    },
                )
            }
            item {
                ListItem(
                    headlineContent = { Text(stringResource(R.string.group)) },
                    supportingContent = { Text(candidate?.training_group ?: "") },
                    leadingContent = {
                        Icon(
                            Icons.Filled.Groups,
                            contentDescription = stringResource(R.string.group),
                        )
                    },
                )
            }
            item {
                ListItem(
                    headlineContent = { Text(stringResource(R.string.course)) },
                    supportingContent = { Text(candidate?.course.toString()) },
                    leadingContent = {
                        Icon(
                            Icons.Filled.School,
                            contentDescription = stringResource(R.string.course),
                        )
                    },
                )
            }
            item {
                Divider()
            }
            item {
                SettingItem(
                    title = stringResource(R.string.my_requests),
                    onClick = { navController.navigate(NavDestinations.CANDIDATE_PARTICIPATIONS_PAGE) },
                ) {
                    FeedbackIconButton(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.my_requests),
                        onClick = { navController.navigate(NavDestinations.CANDIDATE_PARTICIPATIONS_PAGE) },
                    )
                }
            }
            item {
                SettingItem(
                    title = stringResource(R.string.my_projects),
                    onClick = { navController.navigate(NavDestinations.CANDIDATE_PROJECTS_PAGE) },
                ) {
                    FeedbackIconButton(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.my_projects),
                        onClick = { navController.navigate(NavDestinations.CANDIDATE_PROJECTS_PAGE) },
                    )
                }
            }
            item {
                Divider()
            }
            item {
                SettingItem(
                    title = stringResource(R.string.logout),
                    onClick = { onLogoutClick() },
                ) {
                    FeedbackIconButton(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = stringResource(R.string.logout),
                        onClick = { onLogoutClick() },
                    )
                }
            }
        }
    }
}
