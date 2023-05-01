package com.istu.schedule.ui.page.account

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.ui.components.base.CustomIndicator
import com.istu.schedule.ui.components.base.FilledButton
import com.istu.schedule.ui.components.base.InfoBlock
import com.istu.schedule.ui.components.base.SIScrollableTabRow
import com.istu.schedule.ui.components.base.SITabPosition
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations
import kotlinx.coroutines.launch

@Composable
fun AccountPage(
    navController: NavController,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val candidate by viewModel.candidate.observeAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.collectSettingsState()
    }

    if (candidate != null) {
        AuthorizedPage(
            navController = navController,
            candidate = candidate!!
        )
    } else {
        LoginPage(navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthorizedPage(
    navController: NavController,
    candidate: Candidate
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val pages = listOf(
        stringResource(R.string.my_profile),
        stringResource(R.string.my_participations),
        stringResource(R.string.my_projects),
        stringResource(R.string.skills)
    )
    val indicator = @Composable { tabPositions: List<SITabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            Column(modifier = Modifier.statusBarsPadding()) {
                Text(
                    modifier = Modifier.padding(15.dp),
                    text = candidate.fio,
                    style = MaterialTheme.typography.headlineMedium
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(bottom = 10.dp)
                ) {
                    SIScrollableTabRow(
                        selectedTabIndex = pagerState.currentPage,
                        indicator = indicator,
                        edgePadding = 0.dp
                    ) {
                        pages.forEachIndexed { index, title ->
                            Column(
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(end = if (index != pages.size - 1) 20.dp else 0.dp)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null
                                    ) {
                                        coroutineScope.launch {
                                            pagerState.scrollToPage(index)
                                        }
                                    },
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = Color.White,
                                        fontSize = 18.sp
                                    ),
                                    text = title
                                )
                            }
                        }
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .clip(ShapeTop15)
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 23.dp,
                    bottom = 50.dp
                )
        ) {
            HorizontalPager(
                pageCount = pages.size,
                state = pagerState
            ) { page ->
                when (page) {
                    0 -> {
                        ProfilePage(navController = navController, candidate = candidate)
                    }

                    1 -> {}

                    2 -> {}

                    3 -> {}
                }
            }
        }
    }
}

@Composable
fun LoginPage(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.login_to_account),
            style = MaterialTheme.typography.headlineMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        FilledButton(
            modifier = Modifier.padding(top = 25.dp).fillMaxWidth(),
            text = stringResource(R.string.authorize_via_campus),
            onClick = {
                navController.navigate(NavDestinations.PROJFAIR_LOGIN_PAGE)
            }
        )
    }
}

@Composable
fun ProfilePage(
    navController: NavController,
    candidate: Candidate
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.contact_info),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        item {
            InfoBlock(
                title = stringResource(R.string.email),
                description = candidate.email
            )
        }
        item {
            InfoBlock(
                title = stringResource(R.string.phone_number),
                description = candidate.phone.ifBlank {
                    stringResource(R.string.not_specified)
                }
            )
        }
        item {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = HalfGray
            )
        }
        item {
            Text(
                text = stringResource(R.string.additional_information),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        item {
            InfoBlock(
                title = stringResource(R.string.training_group),
                description = candidate.trainingGroup
            )
        }
        item {
            InfoBlock(
                title = stringResource(R.string.course),
                description = candidate.course.toString()
            )
        }
        item {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = HalfGray
            )
        }
        item {
            Text(
                text = stringResource(R.string.skills),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
