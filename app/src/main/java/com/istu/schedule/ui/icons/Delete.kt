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

val Icons.Delete: ImageVector
    get() {
        if (_delete != null) {
            return _delete!!
        }
        _delete = Builder(
            name = "Delete",
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
                moveTo(3.0f, 6.0f)
                horizontalLineTo(5.0f)
                horizontalLineTo(21.0f)
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
                moveTo(8.0f, 6.0f)
                verticalLineTo(4.0f)
                curveTo(8.0f, 3.4696f, 8.2107f, 2.9609f, 8.5858f, 2.5858f)
                curveTo(8.9609f, 2.2107f, 9.4696f, 2.0f, 10.0f, 2.0f)
                horizontalLineTo(14.0f)
                curveTo(14.5304f, 2.0f, 15.0391f, 2.2107f, 15.4142f, 2.5858f)
                curveTo(15.7893f, 2.9609f, 16.0f, 3.4696f, 16.0f, 4.0f)
                verticalLineTo(6.0f)
                moveTo(19.0f, 6.0f)
                verticalLineTo(20.0f)
                curveTo(19.0f, 20.5304f, 18.7893f, 21.0391f, 18.4142f, 21.4142f)
                curveTo(18.0391f, 21.7893f, 17.5304f, 22.0f, 17.0f, 22.0f)
                horizontalLineTo(7.0f)
                curveTo(6.4696f, 22.0f, 5.9609f, 21.7893f, 5.5858f, 21.4142f)
                curveTo(5.2107f, 21.0391f, 5.0f, 20.5304f, 5.0f, 20.0f)
                verticalLineTo(6.0f)
                horizontalLineTo(19.0f)
                close()
            }
        }
            .build()
        return _delete!!
    }

private var _delete: ImageVector? = null
