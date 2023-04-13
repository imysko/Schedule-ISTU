package com.istu.schedule.ui.page.projfair.list.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.CheckboxGroup
import com.istu.schedule.ui.components.base.CheckboxItem
import com.istu.schedule.ui.components.base.FilledButton
import com.istu.schedule.ui.components.base.SIDropdownMenu
import com.istu.schedule.ui.components.base.TextButton
import com.istu.schedule.ui.icons.X
import com.istu.schedule.util.collectAsStateValue

@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun FiltersPage(
    navController: NavController,
    viewModel: FilterViewModel = hiltViewModel(),
) {
    val filtersPageUiState = viewModel.filtersPageUiState.collectAsStateValue()

    val skillsList by viewModel.skillsList.observeAsState(initial = emptyList())

    val specialitiesList by viewModel.specialitiesList.observeAsState(initial = emptyList())

    val statusesList = mutableListOf(
        CheckboxItem(1, R.string.recruitment_is_open),
        CheckboxItem(2, R.string.processing_of_participants),
        CheckboxItem(4, R.string.completion_of_participants),
        CheckboxItem(5, R.string.in_archive),
    )

    val difficultiesList = mutableListOf(
        CheckboxItem(1, R.string.easy),
        CheckboxItem(2, R.string.medium),
        CheckboxItem(3, R.string.difficult),
    )

    val selectedStatusesList = filtersPageUiState.statusesList

    val selectedDifficultiesList = filtersPageUiState.difficultiesList

    val selectedSkillsList = filtersPageUiState.skillsList

    val selectedSpecialitiesList = filtersPageUiState.specialitiesList

    LaunchedEffect(Unit) {
        viewModel.getSpecialitiesList()
        viewModel.getSkillsList()
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.filters),
                    style = MaterialTheme.typography.titleLarge,
                )
                Column(
                    modifier = Modifier
                        .padding(7.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                        ) {
                            navController.popBackStack()
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = Icons.X,
                        contentDescription = "cross icon",
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = 14.dp, bottom = 4.dp, start = 5.dp),
                text = stringResource(R.string.project_status),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            )
            CheckboxGroup(
                items = statusesList,
                selectedList = selectedStatusesList.toMutableList(),
                onCheckedChange = { viewModel.setStatusesList(it) },
            )
            Divider(Modifier.padding(vertical = 22.dp))
            Text(
                modifier = Modifier.padding(bottom = 14.dp, start = 5.dp),
                text = stringResource(R.string.project_tags),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            )
            SIDropdownMenu(
                listItems = specialitiesList.map { Pair(it.id, it.name) },
                textValue = filtersPageUiState.specialitySearchText,
                onTextValueChange = { viewModel.setSpecialitySearchText(it) },
                selectedItems = selectedSpecialitiesList.toMutableList(),
                placeholder = stringResource(R.string.select_a_specialty),
                onItemSelect = { viewModel.setSpecialitiesList(it) },
            )
            SIDropdownMenu(
                modifier = Modifier.padding(top = 8.dp),
                listItems = skillsList.map { Pair(it.id, it.name) },
                textValue = filtersPageUiState.skillSearchText,
                onTextValueChange = { viewModel.setSkillSearchText(it) },
                selectedItems = selectedSkillsList.toMutableList(),
                placeholder = stringResource(R.string.select_a_skill),
                onItemSelect = { viewModel.setSkillsList(it) },
            )
            Divider(Modifier.padding(vertical = 22.dp))
            Text(
                modifier = Modifier.padding(bottom = 4.dp, start = 5.dp),
                text = stringResource(R.string.level_of_difficulty),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            )
            CheckboxGroup(
                items = difficultiesList,
                selectedList = selectedDifficultiesList.toMutableList(),
                onCheckedChange = { viewModel.setDifficultiesList(it) },
            )
            FilledButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp)
                    .height(42.dp),
                text = stringResource(R.string.find),
                onClick = { },
            )
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .height(42.dp),
                text = stringResource(R.string.reset_filter),
                onClick = { viewModel.resetFilters() },
            )
            Spacer(modifier = Modifier.height(128.dp))
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}
