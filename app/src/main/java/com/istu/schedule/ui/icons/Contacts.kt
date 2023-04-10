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

val Icons.Contacts: ImageVector
    get() {
        if (_contacts != null) {
            return _contacts!!
        }
        _contacts = Builder(
            name = "Contacts",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
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
                moveTo(21.0f, 10.0f)
                curveTo(21.0f, 17.0f, 12.0f, 23.0f, 12.0f, 23.0f)
                curveTo(12.0f, 23.0f, 3.0f, 17.0f, 3.0f, 10.0f)
                curveTo(3.0f, 7.613f, 3.9482f, 5.3239f, 5.636f, 3.636f)
                curveTo(7.3239f, 1.9482f, 9.6131f, 1.0f, 12.0f, 1.0f)
                curveTo(14.3869f, 1.0f, 16.6761f, 1.9482f, 18.364f, 3.636f)
                curveTo(20.0518f, 5.3239f, 21.0f, 7.613f, 21.0f, 10.0f)
                close()
            }
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
                moveTo(12.0f, 13.0f)
                curveTo(13.6569f, 13.0f, 15.0f, 11.6569f, 15.0f, 10.0f)
                curveTo(15.0f, 8.3432f, 13.6569f, 7.0f, 12.0f, 7.0f)
                curveTo(10.3431f, 7.0f, 9.0f, 8.3432f, 9.0f, 10.0f)
                curveTo(9.0f, 11.6569f, 10.3431f, 13.0f, 12.0f, 13.0f)
                close()
            }
        }
            .build()
        return _contacts!!
    }

private var _contacts: ImageVector? = null
