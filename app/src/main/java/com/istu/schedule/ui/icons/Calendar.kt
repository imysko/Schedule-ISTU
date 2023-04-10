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

val Icons.Calendar: ImageVector
    get() {
        if (_calendar != null) {
            return _calendar!!
        }
        _calendar = Builder(
            name = "Calendar",
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
                moveTo(16.0f, 2.0f)
                verticalLineTo(6.0f)
                moveTo(8.0f, 2.0f)
                verticalLineTo(6.0f)
                moveTo(3.0f, 10.0f)
                horizontalLineTo(21.0f)
                moveTo(5.0f, 4.0f)
                horizontalLineTo(19.0f)
                curveTo(20.1046f, 4.0f, 21.0f, 4.8954f, 21.0f, 6.0f)
                verticalLineTo(20.0f)
                curveTo(21.0f, 21.1046f, 20.1046f, 22.0f, 19.0f, 22.0f)
                horizontalLineTo(5.0f)
                curveTo(3.8954f, 22.0f, 3.0f, 21.1046f, 3.0f, 20.0f)
                verticalLineTo(6.0f)
                curveTo(3.0f, 4.8954f, 3.8954f, 4.0f, 5.0f, 4.0f)
                close()
            }
        }
            .build()
        return _calendar!!
    }

private var _calendar: ImageVector? = null
