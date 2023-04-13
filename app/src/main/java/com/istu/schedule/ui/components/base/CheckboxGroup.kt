package com.istu.schedule.ui.components.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxGroup(
    modifier: Modifier = Modifier,
    items: MutableList<CheckboxItem>,
    selectedList: MutableList<Int>,
    onCheckedChange: (List<Int>) -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        for (item in items) {
            SICheckbox(
                modifier = Modifier
                    .clip(com.istu.schedule.ui.theme.Shape5)
                    .clickable {
                        if (item.id in selectedList) {
                            selectedList.remove(item.id)
                        } else {
                            selectedList.add(item.id)
                        }
                        onCheckedChange(selectedList)
                    }
                    .padding(vertical = 5.dp),
                text = stringResource(item.stringId),
                onCheckedChange = {
                    if (item.id in selectedList) {
                        selectedList.remove(item.id)
                    } else {
                        selectedList.add(item.id)
                    }
                    onCheckedChange(selectedList)
                },
                checkedState = item.id in selectedList,
            )
        }
    }
}

data class CheckboxItem(
    val id: Int,
    val stringId: Int,
)
