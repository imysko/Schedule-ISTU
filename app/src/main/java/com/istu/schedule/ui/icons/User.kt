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

val Icons.User: ImageVector
    get() {
        if (_user != null) {
            return _user!!
        }
        _user = Builder(
            name = "User",
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
                moveTo(20.0f, 21.0f)
                verticalLineTo(19.0f)
                curveTo(20.0f, 17.9391f, 19.5786f, 16.9217f, 18.8284f, 16.1716f)
                curveTo(18.0783f, 15.4214f, 17.0609f, 15.0f, 16.0f, 15.0f)
                horizontalLineTo(8.0f)
                curveTo(6.9391f, 15.0f, 5.9217f, 15.4214f, 5.1716f, 16.1716f)
                curveTo(4.4214f, 16.9217f, 4.0f, 17.9391f, 4.0f, 19.0f)
                verticalLineTo(21.0f)
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
                moveTo(12.0f, 11.0f)
                curveTo(14.2091f, 11.0f, 16.0f, 9.2091f, 16.0f, 7.0f)
                curveTo(16.0f, 4.7909f, 14.2091f, 3.0f, 12.0f, 3.0f)
                curveTo(9.7909f, 3.0f, 8.0f, 4.7909f, 8.0f, 7.0f)
                curveTo(8.0f, 9.2091f, 9.7909f, 11.0f, 12.0f, 11.0f)
                close()
            }
        }
            .build()
        return _user!!
    }

private var _user: ImageVector? = null
