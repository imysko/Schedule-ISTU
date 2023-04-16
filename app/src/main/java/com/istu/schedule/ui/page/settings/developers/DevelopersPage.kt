package com.istu.schedule.ui.page.settings.developers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.istu.schedule.ui.components.base.SIScaffold

@Composable
fun DevelopersPage() {
    SIScaffold(
        content = {
            Column {
                Row {
                    Text(
                        text = "About developers",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Row {
                    Text(
                        text = "If you encounter a problem or have suggestions, write to us",
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    )
                }
                Row {
                }
                Row {
                }
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun DevelopersPagePreview() {
    DevelopersPage()
}
