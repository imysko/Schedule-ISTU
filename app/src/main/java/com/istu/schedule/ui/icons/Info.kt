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

val Icons.Info: ImageVector
    get() {
        if (_info != null) {
            return _info!!
        }
        _info = Builder(
            name = "Info",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFffffff)),
                strokeLineWidth = 1.60183f,
                strokeLineCap = Round,
                strokeLineJoin =
                StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(11.9966f, 22.0f)
                curveTo(17.5175f, 22.0f, 21.9931f, 17.5228f, 21.9931f, 12.0f)
                curveTo(21.9931f, 6.4771f, 17.5175f, 2.0f, 11.9966f, 2.0f)
                curveTo(6.4756f, 2.0f, 2.0f, 6.4771f, 2.0f, 12.0f)
                curveTo(2.0f, 17.5228f, 6.4756f, 22.0f, 11.9966f, 22.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFffffff)),
                strokeLineWidth = 1.60183f,
                strokeLineCap = Round,
                strokeLineJoin =
                StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(9.0859f, 9.0006f)
                curveTo(9.321f, 8.3322f, 9.7849f, 7.7687f, 10.3954f, 7.4097f)
                curveTo(11.006f, 7.0507f, 11.7239f, 6.9195f, 12.422f, 7.0393f)
                curveTo(13.12f, 7.1591f, 13.7531f, 7.5221f, 14.2092f, 8.0641f)
                curveTo(14.6654f, 8.6061f, 14.915f, 9.2921f, 14.9139f, 10.0006f)
                curveTo(14.9139f, 12.0006f, 11.915f, 13.0006f, 11.915f, 13.0006f)
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFffffff)),
                strokeLineWidth = 1.60183f,
                strokeLineCap = Round,
                strokeLineJoin =
                StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(11.9961f, 17.0f)
                horizontalLineTo(12.0061f)
            }
        }
            .build()
        return _info!!
    }

private var _info: ImageVector? = null
