package com.istu.schedule.ui.components.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.theme.Shape5

@Composable
fun CheckboxGroup(
    items: List<StringResourceItem>,
    selectedList: MutableList<Int>,
    modifier: Modifier = Modifier,
    onCheckedChange: (List<Int>) -> Unit = {}
) {
    Column(modifier = modifier) {
        for (item in items) {
            SICheckbox(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .clip(Shape5)
                    .clickable {
                        if (item.id in selectedList) {
                            selectedList.remove(item.id)
                        } else {
                            selectedList.add(item.id)
                        }
                        onCheckedChange(selectedList)
                    },
                text = stringResource(item.stringId),
                onCheckedChange = {
                    if (item.id in selectedList) {
                        selectedList.remove(item.id)
                    } else {
                        selectedList.add(item.id)
                    }
                    onCheckedChange(selectedList)
                },
                checkedState = item.id in selectedList
            )
        }
    }
}

data class StringResourceItem(
    val id: Int,
    val stringId: Int
)
