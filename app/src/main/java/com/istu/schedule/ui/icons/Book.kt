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

val Icons.Book: ImageVector
    get() {
        if (_book != null) {
            return _book!!
        }
        _book = Builder(
            name = "Book",
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
                moveTo(2.0f, 3.0f)
                horizontalLineTo(8.0f)
                curveTo(9.0609f, 3.0f, 10.0783f, 3.4214f, 10.8284f, 4.1716f)
                curveTo(11.5786f, 4.9217f, 12.0f, 5.9391f, 12.0f, 7.0f)
                verticalLineTo(21.0f)
                curveTo(12.0f, 20.2044f, 11.6839f, 19.4413f, 11.1213f, 18.8787f)
                curveTo(10.5587f, 18.3161f, 9.7956f, 18.0f, 9.0f, 18.0f)
                horizontalLineTo(2.0f)
                verticalLineTo(3.0f)
                close()
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
                moveTo(22.0f, 3.0f)
                horizontalLineTo(16.0f)
                curveTo(14.9391f, 3.0f, 13.9217f, 3.4214f, 13.1716f, 4.1716f)
                curveTo(12.4214f, 4.9217f, 12.0f, 5.9391f, 12.0f, 7.0f)
                verticalLineTo(21.0f)
                curveTo(12.0f, 20.2044f, 12.3161f, 19.4413f, 12.8787f, 18.8787f)
                curveTo(13.4413f, 18.3161f, 14.2044f, 18.0f, 15.0f, 18.0f)
                horizontalLineTo(22.0f)
                verticalLineTo(3.0f)
                close()
            }
        }
            .build()
        return _book!!
    }

private var _book: ImageVector? = null
