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

val Icons.Services: ImageVector
    get() {
        if (_services != null) {
            return _services!!
        }
        _services = Builder(
            name = "Services",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
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
                moveTo(17.5f, 21.0f)
                curveTo(19.433f, 21.0f, 21.0f, 19.433f, 21.0f, 17.5f)
                curveTo(21.0f, 15.567f, 19.433f, 14.0f, 17.5f, 14.0f)
                curveTo(15.567f, 14.0f, 14.0f, 15.567f, 14.0f, 17.5f)
                curveTo(14.0f, 19.433f, 15.567f, 21.0f, 17.5f, 21.0f)
                close()
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
                moveTo(6.5f, 10.0f)
                curveTo(10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 6.5f)
                curveTo(10.0f, 3.0f, 10.0f, 3.0f, 6.5f, 3.0f)
                curveTo(3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 6.5f)
                curveTo(3.0f, 10.0f, 3.0f, 10.0f, 6.5f, 10.0f)
                close()
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
                moveTo(17.5f, 10.0f)
                curveTo(21.0f, 10.0f, 21.0f, 10.0f, 21.0f, 6.5f)
                curveTo(21.0f, 3.0f, 21.0f, 3.0f, 17.5f, 3.0f)
                curveTo(14.0f, 3.0f, 14.0f, 3.0f, 14.0f, 6.5f)
                curveTo(14.0f, 10.0f, 14.0f, 10.0f, 17.5f, 10.0f)
                close()
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
                moveTo(6.5f, 21.0f)
                curveTo(10.0f, 21.0f, 10.0f, 21.0f, 10.0f, 17.5f)
                curveTo(10.0f, 14.0f, 10.0f, 14.0f, 6.5f, 14.0f)
                curveTo(3.0f, 14.0f, 3.0f, 14.0f, 3.0f, 17.5f)
                curveTo(3.0f, 21.0f, 3.0f, 21.0f, 6.5f, 21.0f)
                close()
            }
        }
            .build()
        return _services!!
    }

private var _services: ImageVector? = null
