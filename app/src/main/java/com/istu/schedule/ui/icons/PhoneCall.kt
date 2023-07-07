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

val Icons.PhoneCall: ImageVector
    get() {
        if (_phoneCall != null) {
            return _phoneCall!!
        }
        _phoneCall = Builder(
            name = "Phone-call",
            defaultWidth = 25.0.dp,
            defaultHeight =
            25.0.dp,
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
                moveTo(15.6772f, 5.21f)
                curveTo(16.6946f, 5.4085f, 17.6296f, 5.9061f, 18.3626f, 6.6391f)
                curveTo(19.0956f, 7.3721f, 19.5932f, 8.3072f, 19.7917f, 9.3246f)
                moveTo(15.6772f, 1.0434f)
                curveTo(17.791f, 1.2782f, 19.7621f, 2.2248f, 21.267f, 3.7278f)
                curveTo(22.7718f, 5.2307f, 23.7209f, 7.2007f, 23.9584f, 9.3142f)
                moveTo(22.9167f, 17.6267f)
                verticalLineTo(20.7517f)
                curveTo(22.9179f, 21.0418f, 22.8585f, 21.329f, 22.7423f, 21.5948f)
                curveTo(22.6261f, 21.8606f, 22.4556f, 22.0992f, 22.2418f, 22.2953f)
                curveTo(22.028f, 22.4914f, 21.7757f, 22.6407f, 21.5009f, 22.7337f)
                curveTo(21.226f, 22.8266f, 20.9348f, 22.8611f, 20.6459f, 22.835f)
                curveTo(17.4405f, 22.4867f, 14.3615f, 21.3914f, 11.6563f, 19.6371f)
                curveTo(9.1395f, 18.0378f, 7.0056f, 15.904f, 5.4063f, 13.3871f)
                curveTo(3.6459f, 10.6696f, 2.5503f, 7.5757f, 2.2084f, 4.3559f)
                curveTo(2.1824f, 4.0678f, 2.2166f, 3.7775f, 2.3089f, 3.5034f)
                curveTo(2.4012f, 3.2293f, 2.5496f, 2.9774f, 2.7446f, 2.7638f)
                curveTo(2.9396f, 2.5502f, 3.1769f, 2.3795f, 3.4415f, 2.2627f)
                curveTo(3.7061f, 2.1458f, 3.9921f, 2.0853f, 4.2813f, 2.085f)
                horizontalLineTo(7.4063f)
                curveTo(7.9118f, 2.0801f, 8.4019f, 2.2591f, 8.7852f, 2.5887f)
                curveTo(9.1685f, 2.9183f, 9.4189f, 3.3761f, 9.4897f, 3.8767f)
                curveTo(9.6216f, 4.8768f, 9.8662f, 5.8587f, 10.2188f, 6.8038f)
                curveTo(10.359f, 7.1766f, 10.3893f, 7.5818f, 10.3062f, 7.9714f)
                curveTo(10.2231f, 8.3609f, 10.0301f, 8.7185f, 9.7501f, 9.0017f)
                lineTo(8.4272f, 10.3246f)
                curveTo(9.91f, 12.9325f, 12.0693f, 15.0917f, 14.6772f, 16.5746f)
                lineTo(16.0001f, 15.2517f)
                curveTo(16.2833f, 14.9716f, 16.6409f, 14.7786f, 17.0304f, 14.6955f)
                curveTo(17.42f, 14.6125f, 17.8252f, 14.6428f, 18.198f, 14.7829f)
                curveTo(19.1431f, 15.1356f, 20.125f, 15.3802f, 21.1251f, 15.5121f)
                curveTo(21.6311f, 15.5835f, 22.0932f, 15.8384f, 22.4235f, 16.2283f)
                curveTo(22.7539f, 16.6181f, 22.9294f, 17.1158f, 22.9167f, 17.6267f)
                close()
            }
        }
            .build()
        return _phoneCall!!
    }

private var _phoneCall: ImageVector? = null
