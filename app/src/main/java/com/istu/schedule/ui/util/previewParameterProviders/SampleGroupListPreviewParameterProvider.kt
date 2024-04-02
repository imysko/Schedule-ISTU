package com.istu.schedule.ui.util.previewParameterProviders

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.istu.schedule.domain.entities.schedule.Group

class SampleGroupListPreviewParameterProvider : PreviewParameterProvider<List<Group>> {

    override val values = sequenceOf(
        listOf(
            Group(
                groupId = 0,
                name = "ИСТб-20-1",
                course = 3,
                instituteId = 0,
                institute = null,
                isActive = true
            ),
            Group(
                groupId = 0,
                name = "ИСТб-20-2",
                course = 3,
                instituteId = 0,
                institute = null,
                isActive = true
            ),
            Group(
                groupId = 0,
                name = "ИСТб-20-3",
                course = 3,
                instituteId = 0,
                institute = null,
                isActive = true
            )
        )
    )
}
