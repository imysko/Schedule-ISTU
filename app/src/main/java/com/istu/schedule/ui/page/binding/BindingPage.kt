package com.istu.schedule.ui.page.binding

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.DisplayText
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.components.base.Subtitle

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

                Subtitle(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Who are you?"
                )
                ChooseUserStatus()

                Subtitle(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Choose your institute"
                )
                ChooseInstitute(
                    instituteList = instituteList
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun BindingPagePreview() {
    BindingPage()
}
