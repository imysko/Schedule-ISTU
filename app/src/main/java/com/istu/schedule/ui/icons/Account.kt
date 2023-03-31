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

val Icons.Account: ImageVector
    get() {
        if (_account != null) {
            return _account!!
        }
        _account = Builder(
            name = "Account",
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
                moveTo(20.0898f, 21.5f)
                verticalLineTo(19.5f)
                curveTo(20.0898f, 18.4391f, 19.6684f, 17.4217f, 18.9183f, 16.6716f)
                curveTo(18.1681f, 15.9214f, 17.1507f, 15.5f, 16.0898f, 15.5f)
                horizontalLineTo(8.0898f)
                curveTo(7.029f, 15.5f, 6.0116f, 15.9214f, 5.2614f, 16.6716f)
                curveTo(4.5113f, 17.4217f, 4.0898f, 18.4391f, 4.0898f, 19.5f)
                verticalLineTo(21.5f)
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
                moveTo(12.0898f, 11.5f)
                curveTo(14.299f, 11.5f, 16.0898f, 9.7091f, 16.0898f, 7.5f)
                curveTo(16.0898f, 5.2909f, 14.299f, 3.5f, 12.0898f, 3.5f)
                curveTo(9.8807f, 3.5f, 8.0898f, 5.2909f, 8.0898f, 7.5f)
                curveTo(8.0898f, 9.7091f, 9.8807f, 11.5f, 12.0898f, 11.5f)
                close()
            }
        }
            .build()
        return _account!!
    }

private var _account: ImageVector? = null

@Preview
@Composable
private fun IconPreview() {
    Image(imageVector = Icons.Account, contentDescription = null)
}
