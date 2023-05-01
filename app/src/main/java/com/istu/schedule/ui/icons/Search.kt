package com.istu.schedule.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Search: ImageVector
    get() {
        if (_search != null) {
            return _search!!
        }
        _search = Builder(
            name = "Search",
            defaultWidth = 25.0.dp,
            defaultHeight = 25.0.dp,
            viewportWidth = 25.0f,
            viewportHeight = 25.0f
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFffffff)),
                strokeLineWidth = 2.0f,
                strokeLineCap = Round,
                strokeLineJoin =
                StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(20.576f, 20.5763f)
                lineTo(16.6725f, 16.6728f)
                moveTo(18.7814f, 11.6027f)
                curveTo(18.7814f, 15.5674f, 15.5673f, 18.7815f, 11.6025f, 18.7815f)
                curveTo(7.6377f, 18.7815f, 4.4236f, 15.5674f, 4.4236f, 11.6027f)
                curveTo(4.4236f, 7.6379f, 7.6377f, 4.4238f, 11.6025f, 4.4238f)
                curveTo(15.5673f, 4.4238f, 18.7814f, 7.6379f, 18.7814f, 11.6027f)
                close()
            }
        }
            .build()
        return _search!!
    }

private var _search: ImageVector? = null
