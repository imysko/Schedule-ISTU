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

val Icons.Edit: ImageVector
    get() {
        if (_edit != null) {
            return _edit!!
        }
        _edit = Builder(
            name = "Edit",
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
                moveTo(17.0f, 3.0f)
                curveTo(17.2626f, 2.7373f, 17.5744f, 2.529f, 17.9176f, 2.3869f)
                curveTo(18.2608f, 2.2447f, 18.6286f, 2.1716f, 19.0f, 2.1716f)
                curveTo(19.3714f, 2.1716f, 19.7392f, 2.2447f, 20.0824f, 2.3869f)
                curveTo(20.4256f, 2.529f, 20.7374f, 2.7373f, 21.0f, 3.0f)
                curveTo(21.2626f, 3.2626f, 21.471f, 3.5744f, 21.6131f, 3.9176f)
                curveTo(21.7553f, 4.2608f, 21.8284f, 4.6286f, 21.8284f, 5.0f)
                curveTo(21.8284f, 5.3714f, 21.7553f, 5.7392f, 21.6131f, 6.0824f)
                curveTo(21.471f, 6.4255f, 21.2626f, 6.7373f, 21.0f, 7.0f)
                lineTo(7.5f, 20.5f)
                lineTo(2.0f, 22.0f)
                lineTo(3.5f, 16.5f)
                lineTo(17.0f, 3.0f)
                close()
            }
        }
            .build()
        return _edit!!
    }

private var _edit: ImageVector? = null
