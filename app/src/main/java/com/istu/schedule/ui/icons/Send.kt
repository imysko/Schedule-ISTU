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

val Icons.Send: ImageVector
    get() {
        if (_send != null) {
            return _send!!
        }
        _send = Builder(
            name = "Send",
            defaultWidth = 25.0.dp,
            defaultHeight = 25.0.dp,
            viewportWidth = 25.0f,
            viewportHeight = 25.0f
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF383838)),
                strokeLineWidth = 2.0f,
                strokeLineCap = Round,
                strokeLineJoin =
                StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(22.9166f, 2.0833f)
                lineTo(11.4583f, 13.5417f)
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF383838)),
                strokeLineWidth = 2.0f,
                strokeLineCap = Round,
                strokeLineJoin =
                StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(22.9166f, 2.0833f)
                lineTo(15.6249f, 22.9167f)
                lineTo(11.4583f, 13.5417f)
                lineTo(2.0833f, 9.375f)
                lineTo(22.9166f, 2.0833f)
                close()
            }
        }
            .build()
        return _send!!
    }

private var _send: ImageVector? = null
