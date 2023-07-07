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

val Icons.Mail: ImageVector
    get() {
        if (_mail != null) {
            return _mail!!
        }
        _mail = Builder(
            name = "Mail",
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
                moveTo(4.1666f, 4.1667f)
                horizontalLineTo(20.8333f)
                curveTo(21.9791f, 4.1667f, 22.9166f, 5.1042f, 22.9166f, 6.25f)
                verticalLineTo(18.75f)
                curveTo(22.9166f, 19.8958f, 21.9791f, 20.8333f, 20.8333f, 20.8333f)
                horizontalLineTo(4.1666f)
                curveTo(3.0208f, 20.8333f, 2.0833f, 19.8958f, 2.0833f, 18.75f)
                verticalLineTo(6.25f)
                curveTo(2.0833f, 5.1042f, 3.0208f, 4.1667f, 4.1666f, 4.1667f)
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
                moveTo(22.9166f, 6.25f)
                lineTo(12.4999f, 13.5417f)
                lineTo(2.0833f, 6.25f)
            }
        }
            .build()
        return _mail!!
    }

private var _mail: ImageVector? = null
