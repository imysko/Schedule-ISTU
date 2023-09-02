package com.istu.schedule.ui.page.projfair.list.filter

import android.annotation.SuppressLint
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.data.model.ProjfairFiltersState
import com.istu.schedule.domain.model.projfair.Skill
import com.istu.schedule.domain.model.projfair.SkillCategory
import com.istu.schedule.domain.model.projfair.Speciality
import com.istu.schedule.ui.components.base.CheckboxGroup
import com.istu.schedule.ui.components.base.SIDropdownMenu
import com.istu.schedule.ui.components.base.StringResourceItem
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.components.base.button.TextButton
import com.istu.schedule.ui.icons.X
import com.istu.schedule.ui.theme.AppTheme

@Composable
fun FiltersPage(
    navController: NavController,
    viewModel: FilterViewModel = hiltViewModel()
) {
    val skillsList by viewModel.skillsList.observeAsState(initial = emptyList())
    val specialitiesList by viewModel.specialitiesList.observeAsState(initial = emptyList())

    val filters = viewModel.getFilters()

    LaunchedEffect(Unit) {
        viewModel.getSpecialitiesList()
        viewModel.getSkillsList()
    }

    FiltersPage(
        filters = filters,
        skillsList = skillsList,
        specialitiesList = specialitiesList,
        onFindPressed = { viewModel.setFilters(it) },
        onBackPressed = { navController.popBackStack() }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FiltersPage(
    filters: ProjfairFiltersState,
    skillsList: List<Skill>,
    specialitiesList: List<Speciality>,
    onFindPressed: (ProjfairFiltersState) -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    var selectedStatusesList by remember { mutableStateOf(filters.statusesList) }
    var selectedDifficultiesList by remember { mutableStateOf(filters.difficultiesList) }
    var selectedSpecialitiesList by remember { mutableStateOf(filters.specialitiesList) }
    var selectedSkillsList by remember { mutableStateOf(filters.skillsList) }
    var specialitySearchText by remember { mutableStateOf("") }
    var skillSearchText by remember { mutableStateOf("") }

    val statusesList = listOf(
        StringResourceItem(1, R.string.recruitment_is_open),
        StringResourceItem(2, R.string.active),
        StringResourceItem(4, R.string.in_archive),
        StringResourceItem(5, R.string.processing_of_participants)
    )

    val difficultiesList = listOf(
        StringResourceItem(1, R.string.easy),
        StringResourceItem(2, R.string.medium),
        StringResourceItem(3, R.string.difficult)
    )

    Scaffold {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.filters),
                    style = AppTheme.typography.pageTitle
                )
                Column(
                    modifier = Modifier
                        .padding(7.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onBackPressed() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.X,
                        contentDescription = "cross icon",
                        tint = AppTheme.colorScheme.secondary
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = 14.dp, bottom = 4.dp, start = 5.dp),
                text = stringResource(R.string.project_status),
                style = AppTheme.typography.subtitle.copy(fontWeight = FontWeight.Bold)
            )
            CheckboxGroup(
                items = statusesList,
                selectedList = selectedStatusesList.toMutableList(),
                onCheckedChange = { selectedStatusesList = it }
            )
            HorizontalDivider(Modifier.padding(vertical = 22.dp))
            Text(
                modifier = Modifier.padding(bottom = 14.dp, start = 5.dp),
                text = stringResource(R.string.project_tags),
                style = AppTheme.typography.subtitle.copy(fontWeight = FontWeight.Bold)
            )
            SIDropdownMenu(
                listItems = specialitiesList.map { Pair(it.id, it.name) },
                textValue = specialitySearchText,
                onTextValueChange = { specialitySearchText = it },
                selectedItems = selectedSpecialitiesList.toMutableList(),
                placeholder = stringResource(R.string.select_a_specialty),
                onItemSelect = { selectedSpecialitiesList = it }
            )
            SIDropdownMenu(
                modifier = Modifier.padding(top = 8.dp),
                listItems = skillsList.map { Pair(it.id, it.name) },
                textValue = skillSearchText,
                onTextValueChange = { skillSearchText = it },
                selectedItems = selectedSkillsList.toMutableList(),
                placeholder = stringResource(R.string.select_a_skill),
                onItemSelect = { selectedSkillsList = it }
            )
            HorizontalDivider(Modifier.padding(vertical = 22.dp))
            Text(
                modifier = Modifier.padding(bottom = 4.dp, start = 5.dp),
                text = stringResource(R.string.level_of_difficulty),
                style = AppTheme.typography.subtitle.copy(fontWeight = FontWeight.Bold)
            )
            CheckboxGroup(
                items = difficultiesList,
                selectedList = selectedDifficultiesList.toMutableList(),
                onCheckedChange = { selectedDifficultiesList = it }
            )
            FilledButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp)
                    .height(42.dp),
                text = stringResource(R.string.find),
                onClick = {
                    val projfairFiltersState = ProjfairFiltersState(
                        isChanged = true,
                        statusesList = selectedStatusesList,
                        difficultiesList = selectedDifficultiesList,
                        specialitiesList = selectedSpecialitiesList,
                        skillsList = selectedSkillsList
                    )
                    onFindPressed(projfairFiltersState)
                    onBackPressed()
                }
            )
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .height(42.dp),
                text = stringResource(R.string.reset_filter),
                onClick = {
                    selectedStatusesList = listOf()
                    selectedDifficultiesList = listOf()
                    selectedSpecialitiesList = listOf()
                    selectedSkillsList = listOf()
                    specialitySearchText = ""
                    skillSearchText = ""
                }
            )
            Spacer(modifier = Modifier.height(72.dp))
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Preview
@Composable
fun PreviewComposablePage() {
    AppTheme {
        FiltersPage(
            filters = ProjfairFiltersState(),
            skillsList = listOf(Skill(1, "Skill", SkillCategory(1, "Category"))),
            specialitiesList = listOf()
        )
    }
}
