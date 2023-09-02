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
import androidx.compose.material3.HorizontalDivider
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
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.ui.icons.Forward
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.ShapeTop15

@Composable
fun ChooseUserStatus(
    selectedGroupDescription: String?,
    selectedTeacherDescription: String?,
    onBackClick: () -> Unit,
    selectUserStatus: (status: UserStatus) -> Unit
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
                LazyColumn(
                    modifier = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    item {
                        UserStatusItem(
                            title = stringResource(id = R.string.is_student),
                            description = selectedGroupDescription
                                ?: stringResource(id = R.string.not_assigned),
                            onClick = { selectUserStatus(UserStatus.STUDENT) }
                        )
                    }
                    item {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp),
                            color = HalfGray
                        )
                    }
                    item {
                        UserStatusItem(
                            title = stringResource(id = R.string.is_teacher),
                            description = selectedTeacherDescription
                                ?: stringResource(id = R.string.not_assigned),
                            onClick = { selectUserStatus(UserStatus.TEACHER) }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun UserStatusItem(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Shape10)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = AppTheme.typography.subtitle
                )
                Icon(
                    modifier = Modifier.size(15.dp),
                    imageVector = Icons.Forward,
                    tint = AppTheme.colorScheme.secondary,
                    contentDescription = stringResource(id = R.string.forward)
                )
            }
            Text(
                text = description,
                style = AppTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = AppTheme.colorScheme.primary
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ChooseUserStatusPreview() {
    AppTheme {
        ChooseUserStatus(
            selectedGroupDescription = null,
            selectedTeacherDescription = null,
            onBackClick = { },
            selectUserStatus = { }
        )
    }
}
