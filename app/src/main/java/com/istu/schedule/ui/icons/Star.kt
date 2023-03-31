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

public val Icons.Star: ImageVector
    get() {
        if (_star != null) {
            return _star!!
        }
        _star = Builder(
            name = "Star",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFffffff)),
                strokeLineWidth = 1.53755f,
                strokeLineCap = Round,
                strokeLineJoin =
                StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(12.0f, 2.0f)
                lineTo(15.09f, 8.26f)
                lineTo(22.0f, 9.27f)
                lineTo(17.0f, 14.14f)
                lineTo(18.18f, 21.02f)
                lineTo(12.0f, 17.77f)
                lineTo(5.82f, 21.02f)
                lineTo(7.0f, 14.14f)
                lineTo(2.0f, 9.27f)
                lineTo(8.91f, 8.26f)
                lineTo(12.0f, 2.0f)
                close()
            }
        }
            .build()
        return _star!!
    }

private var _star: ImageVector? = null
