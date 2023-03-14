package com.example.schedule_istu.ui.page.settings.developers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevelopersPage() {
    Scaffold(
        content = {
            Column {
                Row {
                    Text(
                        text = "About developers",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold
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
        }
    )
}

@Composable
@Preview(showBackground = true)
fun DevelopersPagePreview() {
    DevelopersPage()
}