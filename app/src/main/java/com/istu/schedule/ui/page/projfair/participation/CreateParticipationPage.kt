package com.istu.schedule.ui.page.projfair.participation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.SITextField
import com.istu.schedule.ui.components.base.StringResourceItem
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.components.base.button.radio.RadioButtonWithText
import com.istu.schedule.ui.icons.Check
import com.istu.schedule.ui.icons.X
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Green

@Composable
fun CreateParticipationPage(
    navController: NavController,
    viewModel: CreateParticipationViewModel = hiltViewModel()
) {
    val selectedPriorityId by viewModel.selectedPriorityId.observeAsState(initial = 1)

    val prioritiesList = mutableListOf(
        StringResourceItem(1, R.string.highest_priority),
        StringResourceItem(2, R.string.medium_priority),
        StringResourceItem(3, R.string.low_priority)
    )

    CreateParticipationPage(
        prioritiesList = prioritiesList,
        selectedPriority = selectedPriorityId,
        onBackClick = { navController.popBackStack() },
        onCreateClick = { viewModel.createParticipation() },
        onSelect = { }
    )
}

@Composable
fun CreateParticipationPage(
    prioritiesList: MutableList<StringResourceItem>,
    selectedPriority: Int,
    onBackClick: () -> Unit,
    onCreateClick: () -> Unit,
    onSelect: (Int) -> Unit
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .statusBarsPadding()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(0.7f),
                        text = stringResource(R.string.send_participation_page_title),
                        style = AppTheme.typography.title
                    )
                    Column(
                        modifier = Modifier
                            .padding(7.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) { onBackClick() },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.X,
                            contentDescription = "cross icon",
                            tint = AppTheme.colorScheme.secondary
                        )
                    }
                }
            }
            item {
                Divider()
            }
            item {
                Text(
                    text = stringResource(R.string.project),
                    style = AppTheme.typography.title
                )
            }
            item {
                Text(
                    text = stringResource(R.string.fullname),
                    style = AppTheme.typography.subtitle.copy(fontWeight = FontWeight.Bold)
                )
                SITextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = "",
                    onValueChange = {},
                    placeholder = stringResource(R.string.fullname),
                    tailingIcon = {
                        Icon(
                            imageVector = Icons.Check,
                            tint = Green,
                            contentDescription = "check"
                        )
                    }
                )
            }
            item {
                Text(
                    text = stringResource(R.string.training_group),
                    style = AppTheme.typography.subtitle.copy(fontWeight = FontWeight.Bold)
                )
                SITextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = "",
                    onValueChange = {},
                    placeholder = stringResource(R.string.training_group),
                    tailingIcon = {
                        Icon(
                            imageVector = Icons.Check,
                            tint = Green,
                            contentDescription = "check"
                        )
                    }
                )
            }
            item {
                Text(
                    text = stringResource(R.string.email),
                    style = AppTheme.typography.subtitle.copy(fontWeight = FontWeight.Bold)
                )
                SITextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = "",
                    onValueChange = {},
                    placeholder = stringResource(R.string.email),
                    tailingIcon = {
                        Icon(
                            imageVector = Icons.Check,
                            tint = Green,
                            contentDescription = "check"
                        )
                    }
                )
            }
            item {
                Text(
                    text = stringResource(R.string.phone_number),
                    style = AppTheme.typography.subtitle.copy(fontWeight = FontWeight.Bold)
                )
                SITextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = "",
                    onValueChange = {},
                    placeholder = stringResource(R.string.phone_number),
                    tailingIcon = {
                        Icon(
                            imageVector = Icons.Check,
                            tint = Green,
                            contentDescription = "check"
                        )
                    }
                )
            }
            item {
                Text(
                    text = stringResource(R.string.project_priority),
                    style = AppTheme.typography.subtitle.copy(fontWeight = FontWeight.Bold)
                )
                prioritiesList.map { priority ->
                    RadioButtonWithText(
                        modifier = Modifier.padding(top = 10.dp),
                        text = stringResource(id = priority.stringId),
                        selected = selectedPriority == priority.id,
                        onSelect = { onSelect(priority.id) }
                    )
                }
            }
            item {
                FilledButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    text = stringResource(R.string.send_participation),
                    onClick = { onCreateClick() }
                )
            }
            item {
                Spacer(modifier = Modifier.height(128.dp))
                Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSendParticipationPage() {
    val prioritiesList = mutableListOf(
        StringResourceItem(1, R.string.highest_priority),
        StringResourceItem(2, R.string.medium_priority),
        StringResourceItem(3, R.string.low_priority)
    )

    AppTheme {
        CreateParticipationPage(
            prioritiesList = prioritiesList,
            selectedPriority = 1,
            onBackClick = {},
            onCreateClick = {},
            onSelect = {}
        )
    }
}
