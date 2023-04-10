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

val Icons.X: ImageVector
    get() {
        if (_x != null) {
            return _x!!
        }
        _x = Builder(
            name = "X",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth =
            24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFffffff)),
                strokeLineWidth = 2.0f,
                strokeLineCap = Round,
                strokeLineJoin =
                StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(18.0f, 6.0f)
                lineTo(6.0f, 18.0f)
                moveTo(6.0f, 6.0f)
                lineTo(18.0f, 18.0f)
            }
        }
            .build()
        return _x!!
    }

private var _x: ImageVector? = null
