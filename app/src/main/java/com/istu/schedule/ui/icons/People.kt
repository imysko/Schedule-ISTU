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

val Icons.People: ImageVector
    get() {
        if (_people != null) {
            return _people!!
        }
        _people = Builder(
            name = "People",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFffffff)),
                strokeLineWidth = 1.53755f,
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
                strokeLineWidth = 1.53755f,
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
                strokeLineWidth = 1.53755f,
                strokeLineCap = Round,
                strokeLineJoin =
                StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(23.0f, 20.9989f)
                verticalLineTo(18.9989f)
                curveTo(22.9993f, 18.1126f, 22.7044f, 17.2517f, 22.1614f, 16.5512f)
                curveTo(21.6184f, 15.8508f, 20.8581f, 15.3505f, 20.0f, 15.1289f)
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFffffff)),
                strokeLineWidth = 1.53755f,
                strokeLineCap = Round,
                strokeLineJoin =
                StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(16.0f, 3.1289f)
                curveTo(16.8604f, 3.3492f, 17.623f, 3.8496f, 18.1676f, 4.5512f)
                curveTo(18.7122f, 5.2528f, 19.0078f, 6.1157f, 19.0078f, 7.0039f)
                curveTo(19.0078f, 7.8921f, 18.7122f, 8.755f, 18.1676f, 9.4566f)
                curveTo(17.623f, 10.1582f, 16.8604f, 10.6586f, 16.0f, 10.8789f)
            }
        }
            .build()
        return _people!!
    }

private var _people: ImageVector? = null
