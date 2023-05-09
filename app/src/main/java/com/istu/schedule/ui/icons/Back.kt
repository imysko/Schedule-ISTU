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

public val Icons.Back: ImageVector
    get() {
        if (_back != null) {
            return _back!!
        }
        _back = Builder(name = "Back", defaultWidth = 8.0.dp, defaultHeight = 14.0.dp, viewportWidth
                = 8.0f, viewportHeight = 14.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF383838)),
                    strokeLineWidth = 1.83333f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(6.75f, 1.5f)
                lineTo(1.25f, 7.0f)
                lineTo(6.75f, 12.5f)
            }
        }
        .build()
        return _back!!
    }

private var _back: ImageVector? = null
