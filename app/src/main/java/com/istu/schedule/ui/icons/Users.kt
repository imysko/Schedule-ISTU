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

val Icons.Users: ImageVector
    get() {
        if (_users != null) {
            return _users!!
        }
        _users = Builder(
            name = "Users",
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
                moveTo(17.0f, 21.0f)
                verticalLineTo(19.0f)
                curveTo(17.0f, 17.9391f, 16.5786f, 16.9217f, 15.8284f, 16.1716f)
                curveTo(15.0783f, 15.4214f, 14.0609f, 15.0f, 13.0f, 15.0f)
                horizontalLineTo(5.0f)
                curveTo(3.9391f, 15.0f, 2.9217f, 15.4214f, 2.1716f, 16.1716f)
                curveTo(1.4214f, 16.9217f, 1.0f, 17.9391f, 1.0f, 19.0f)
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
                moveTo(9.0f, 11.0f)
                curveTo(11.2091f, 11.0f, 13.0f, 9.2091f, 13.0f, 7.0f)
                curveTo(13.0f, 4.7909f, 11.2091f, 3.0f, 9.0f, 3.0f)
                curveTo(6.7909f, 3.0f, 5.0f, 4.7909f, 5.0f, 7.0f)
                curveTo(5.0f, 9.2091f, 6.7909f, 11.0f, 9.0f, 11.0f)
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
                moveTo(23.0f, 20.9999f)
                verticalLineTo(18.9999f)
                curveTo(22.9993f, 18.1136f, 22.7044f, 17.2527f, 22.1614f, 16.5522f)
                curveTo(21.6184f, 15.8517f, 20.8581f, 15.3515f, 20.0f, 15.1299f)
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
                moveTo(16.0f, 3.1299f)
                curveTo(16.8604f, 3.3502f, 17.623f, 3.8506f, 18.1676f, 4.5522f)
                curveTo(18.7122f, 5.2538f, 19.0078f, 6.1167f, 19.0078f, 7.0049f)
                curveTo(19.0078f, 7.8931f, 18.7122f, 8.756f, 18.1676f, 9.4576f)
                curveTo(17.623f, 10.1592f, 16.8604f, 10.6596f, 16.0f, 10.8799f)
            }
        }
            .build()
        return _users!!
    }

private var _users: ImageVector? = null
