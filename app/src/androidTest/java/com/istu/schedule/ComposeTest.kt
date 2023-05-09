package com.istu.schedule

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.istu.schedule.data.preference.SettingsProvider
import com.istu.schedule.ui.components.base.StringResourceItem
import com.istu.schedule.ui.page.projfair.participation.CreateParticipationPage
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import org.junit.Rule
import org.junit.Test

class ComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun prioritiesListTest() {
        val prioritiesList = mutableListOf(
            StringResourceItem(1, R.string.highest_priority),
            StringResourceItem(2, R.string.medium_priority),
            StringResourceItem(3, R.string.low_priority)
        )

        composeTestRule.setContent {
            SettingsProvider {
                ScheduleISTUTheme {
                    CreateParticipationPage(
                        prioritiesList = prioritiesList,
                        selectedPriority = 1,
                        onBackClick = {},
                        onCreateClick = {},
                        onSelect = {}
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Highest Priority").assertIsDisplayed()
        composeTestRule.onNodeWithText("Medium Priority").assertIsDisplayed()
        composeTestRule.onNodeWithText("Low Priority").assertIsDisplayed()
    }
}
