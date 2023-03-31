package com.istu.schedule.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Icons.StarFilled: ImageVector
    get() {
        if (_starFilled != null) {
            return _starFilled!!
        }
        _starFilled = Builder(
            name = "StarFilled",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFffffff)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
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
        return _starFilled!!
    }

private var _starFilled: ImageVector? = null
