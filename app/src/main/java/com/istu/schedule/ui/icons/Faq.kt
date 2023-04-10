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

val Icons.Faq: ImageVector
    get() {
        if (_faq != null) {
            return _faq!!
        }
        _faq = Builder(
            name = "Faq",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth =
            24.0f,
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
                moveTo(21.0f, 15.0f)
                curveTo(21.0f, 15.5304f, 20.7893f, 16.0391f, 20.4142f, 16.4142f)
                curveTo(20.0391f, 16.7893f, 19.5304f, 17.0f, 19.0f, 17.0f)
                horizontalLineTo(7.0f)
                lineTo(3.0f, 21.0f)
                verticalLineTo(5.0f)
                curveTo(3.0f, 4.4696f, 3.2107f, 3.9609f, 3.5858f, 3.5858f)
                curveTo(3.9609f, 3.2107f, 4.4696f, 3.0f, 5.0f, 3.0f)
                horizontalLineTo(19.0f)
                curveTo(19.5304f, 3.0f, 20.0391f, 3.2107f, 20.4142f, 3.5858f)
                curveTo(20.7893f, 3.9609f, 21.0f, 4.4696f, 21.0f, 5.0f)
                verticalLineTo(15.0f)
                close()
            }
        }
            .build()
        return _faq!!
    }

private var _faq: ImageVector? = null
