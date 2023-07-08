package com.istu.schedule.ui.page.settings.developers

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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.R
import com.istu.schedule.ui.icons.Mail
import com.istu.schedule.ui.icons.PhoneCall
import com.istu.schedule.ui.icons.Send
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.Shape5
import com.istu.schedule.util.dial
import com.istu.schedule.util.sendMail

@Composable
fun DevelopersPage(navController: NavController) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    val plcPhone = stringResource(R.string.project_learning_center_phone)
    val plcEmail = stringResource(R.string.project_learning_center_email)
    val emailSubject = stringResource(R.string.projfair)

    Scaffold(
        containerColor = AppTheme.colorScheme.background,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.developers),
                onBackClick = { navController.popBackStack() }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .padding(
                        start = 15.dp,
                        end = 15.dp,
                        top = 23.dp,
                        bottom = 50.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.address),
                        style = AppTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = AppTheme.colorScheme.primary
                        )
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.address_detail),
                        style = AppTheme.typography.bodyMedium,
                        color = AppTheme.colorScheme.textPrimary
                    )
                }
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = stringResource(R.string.work_schedule),
                            style = AppTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = AppTheme.colorScheme.textPrimary
                        )
                        Text(
                            text = stringResource(R.string.work_schedule_detail),
                            style = AppTheme.typography.bodyMedium,
                            color = AppTheme.colorScheme.textPrimary
                        )
                    }
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
                        text = stringResource(R.string.contact),
                        style = AppTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = AppTheme.colorScheme.primary
                        )
                    )
                }
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = stringResource(R.string.project_learning_center),
                            style = AppTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = AppTheme.colorScheme.textPrimary
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.PhoneCall,
                                contentDescription = "Phone Icon",
                                tint = AppTheme.colorScheme.secondary
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .clip(Shape5)
                                    .clickable { context.dial(phone = plcPhone) },
                                text = stringResource(R.string.project_learning_center_phone),
                                style = AppTheme.typography.bodyMedium,
                                color = AppTheme.colorScheme.primary
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Mail,
                                contentDescription = "Mail Icon",
                                tint = AppTheme.colorScheme.secondary
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .clip(Shape5)
                                    .clickable {
                                        context.sendMail(
                                            to = plcEmail,
                                            subject = emailSubject
                                        )
                                    },
                                text = stringResource(R.string.project_learning_center_email),
                                style = AppTheme.typography.bodyMedium,
                                color = AppTheme.colorScheme.primary
                            )
                        }
                    }
                }
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = stringResource(R.string.developers_contacts),
                            style = AppTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = AppTheme.colorScheme.textPrimary
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Send,
                                contentDescription = "Telegram Icon",
                                tint = AppTheme.colorScheme.secondary
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .clip(Shape5)
                                    .clickable { uriHandler.openUri("https://t.me/progneo") },
                                text = stringResource(R.string.telegram_progneo),
                                style = AppTheme.typography.bodyMedium,
                                color = AppTheme.colorScheme.primary
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Send,
                                contentDescription = "Telegram Icon",
                                tint = AppTheme.colorScheme.secondary
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .clip(Shape5)
                                    .clickable { uriHandler.openUri("https://t.me/ilmysko") },
                                text = stringResource(R.string.telegram_ilmysko),
                                style = AppTheme.typography.bodyMedium,
                                color = AppTheme.colorScheme.primary
                            )
                        }
                    }
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
                        text = stringResource(R.string.personalities),
                        style = AppTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = AppTheme.colorScheme.primary
                        )
                    )
                }
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = stringResource(R.string.head_of_plc),

                            style = AppTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = AppTheme.colorScheme.textPrimary
                        )
                        Text(
                            text = stringResource(R.string.head_of_plc_fio),
                            style = AppTheme.typography.bodyMedium,
                            color = AppTheme.colorScheme.textPrimary,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = stringResource(R.string.admin_of_projfair),
                            style = AppTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = AppTheme.colorScheme.textPrimary
                        )
                        Text(
                            text = stringResource(R.string.admin_of_projfair_fio),
                            style = AppTheme.typography.bodyMedium,
                            color = AppTheme.colorScheme.textPrimary,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewDevelopersPage() {
    AppTheme {
        val navController = rememberNavController()
        DevelopersPage(navController)
    }
}
