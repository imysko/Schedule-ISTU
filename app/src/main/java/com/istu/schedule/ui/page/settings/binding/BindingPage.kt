package com.istu.schedule.ui.page.settings.binding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.DisplayText
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.theme.Shapes

@Composable
fun BindingPage(
    viewModel: BindingViewModel = hiltViewModel()
) {
    val instituteList by viewModel.institutesList.observeAsState(initial = emptyList())

    val endOfListReached by remember {
        derivedStateOf { }
    }

    LaunchedEffect(endOfListReached) {
        viewModel.getInstitutesList()
    }

    AppComposable(
        viewModel = viewModel,
        content = {
            SIScaffold {
                DisplayText(
                    text = "Binding",
                    desc = ""
                )
                LazyColumn() {
                    items (instituteList) {institute ->
                        Card(
                            shape = Shapes.medium,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Column(Modifier.padding(8.dp)) {
                                Text(
                                    institute.instituteTitle!!,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2
                                )
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(128.dp))
                        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                    }
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun BindingPagePreview() {
    BindingPage()
}
