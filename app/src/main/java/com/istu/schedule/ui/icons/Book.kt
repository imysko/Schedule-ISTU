package com.istu.schedule.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

public val Icons.Book: ImageVector
    get() {
        if (_book != null) {
            return _book!!
        }
        _book = Builder(
            name = "Book",
            defaultWidth = 25.0.dp,
            defaultHeight = 25.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFffffff)),
                strokeLineWidth = 2.0f,
                strokeLineCap = Round,
                strokeLineJoin = StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(2.3477f, 3.5f)
                horizontalLineTo(8.3477f)
                curveTo(9.4085f, 3.5f, 10.4259f, 3.9214f, 11.1761f, 4.6716f)
                curveTo(11.9262f, 5.4217f, 12.3477f, 6.4391f, 12.3477f, 7.5f)
                verticalLineTo(21.5f)
                curveTo(12.3477f, 20.7044f, 12.0316f, 19.9413f, 11.469f, 19.3787f)
                curveTo(10.9064f, 18.8161f, 10.1433f, 18.5f, 9.3477f, 18.5f)
                horizontalLineTo(2.3477f)
                verticalLineTo(3.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFffffff)),
                strokeLineWidth = 2.0f,
                strokeLineCap = Round,
                strokeLineJoin = StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(22.3477f, 3.5f)
                horizontalLineTo(16.3477f)
                curveTo(15.2868f, 3.5f, 14.2694f, 3.9214f, 13.5192f, 4.6716f)
                curveTo(12.7691f, 5.4217f, 12.3477f, 6.4391f, 12.3477f, 7.5f)
                verticalLineTo(21.5f)
                curveTo(12.3477f, 20.7044f, 12.6637f, 19.9413f, 13.2263f, 19.3787f)
                curveTo(13.7889f, 18.8161f, 14.552f, 18.5f, 15.3477f, 18.5f)
                horizontalLineTo(22.3477f)
                verticalLineTo(3.5f)
                close()
            }
        }
            .build()
        return _book!!
    }

private var _book: ImageVector? = null

@Preview
@Composable
private fun IconPreview() {
    Image(imageVector = Icons.Book, contentDescription = null)
}
