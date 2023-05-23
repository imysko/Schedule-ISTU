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

public val Icons.Down: ImageVector
    get() {
        if (_down != null) {
            return _down!!
        }
        _down = Builder(name = "Down", defaultWidth = 14.0.dp, defaultHeight = 8.0.dp, viewportWidth
                = 14.0f, viewportHeight = 8.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF383838)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(1.0f, 1.0f)
                lineTo(7.0f, 7.0f)
                lineTo(13.0f, 1.0f)
            }
        }
        .build()
        return _down!!
    }

private var _down: ImageVector? = null
