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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val Icons.Settings: ImageVector
    get() {
        if (_settings != null) {
            return _settings!!
        }
        _settings = Builder(
            name = "Settings",
            defaultWidth = 25.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 25.0f,
            viewportHeight = 24.0f,
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0x00000000)),
                    stroke = SolidColor(Color(0xFFffffff)),
                    strokeLineWidth = 2.0f,
                    strokeLineCap = Round,
                    strokeLineJoin = StrokeJoin.Companion.Round,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(12.7422f, 15.0f)
                    curveTo(14.399f, 15.0f, 15.7422f, 13.6569f, 15.7422f, 12.0f)
                    curveTo(15.7422f, 10.3431f, 14.399f, 9.0f, 12.7422f, 9.0f)
                    curveTo(11.0853f, 9.0f, 9.7422f, 10.3431f, 9.7422f, 12.0f)
                    curveTo(9.7422f, 13.6569f, 11.0853f, 15.0f, 12.7422f, 15.0f)
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
                    moveTo(20.1422f, 15.0f)
                    curveTo(20.0091f, 15.3016f, 19.9694f, 15.6362f, 20.0282f, 15.9606f)
                    curveTo(20.087f, 16.285f, 20.2417f, 16.5843f, 20.4722f, 16.82f)
                    lineTo(20.5322f, 16.88f)
                    curveTo(20.7181f, 17.0657f, 20.8657f, 17.2863f, 20.9663f, 17.5291f)
                    curveTo(21.067f, 17.7719f, 21.1188f, 18.0322f, 21.1188f, 18.295f)
                    curveTo(21.1188f, 18.5578f, 21.067f, 18.8181f, 20.9663f, 19.0609f)
                    curveTo(20.8657f, 19.3037f, 20.7181f, 19.5243f, 20.5322f, 19.71f)
                    curveTo(20.3464f, 19.896f, 20.1259f, 20.0435f, 19.8831f, 20.1441f)
                    curveTo(19.6403f, 20.2448f, 19.38f, 20.2966f, 19.1172f, 20.2966f)
                    curveTo(18.8544f, 20.2966f, 18.5941f, 20.2448f, 18.3513f, 20.1441f)
                    curveTo(18.1085f, 20.0435f, 17.8879f, 19.896f, 17.7022f, 19.71f)
                    lineTo(17.6422f, 19.65f)
                    curveTo(17.4065f, 19.4195f, 17.1072f, 19.2648f, 16.7828f, 19.206f)
                    curveTo(16.4584f, 19.1472f, 16.1238f, 19.1869f, 15.8222f, 19.32f)
                    curveTo(15.5264f, 19.4468f, 15.2742f, 19.6572f, 15.0965f, 19.9255f)
                    curveTo(14.9188f, 20.1938f, 14.8235f, 20.5082f, 14.8222f, 20.83f)
                    verticalLineTo(21.0f)
                    curveTo(14.8222f, 21.5304f, 14.6115f, 22.0391f, 14.2364f, 22.4142f)
                    curveTo(13.8613f, 22.7893f, 13.3526f, 23.0f, 12.8222f, 23.0f)
                    curveTo(12.2918f, 23.0f, 11.783f, 22.7893f, 11.408f, 22.4142f)
                    curveTo(11.0329f, 22.0391f, 10.8222f, 21.5304f, 10.8222f, 21.0f)
                    verticalLineTo(20.91f)
                    curveTo(10.8144f, 20.579f, 10.7073f, 20.258f, 10.5147f, 19.9887f)
                    curveTo(10.3221f, 19.7194f, 10.0529f, 19.5143f, 9.7422f, 19.4f)
                    curveTo(9.4406f, 19.2669f, 9.106f, 19.2272f, 8.7816f, 19.286f)
                    curveTo(8.4572f, 19.3448f, 8.1579f, 19.4995f, 7.9222f, 19.73f)
                    lineTo(7.8622f, 19.79f)
                    curveTo(7.6764f, 19.976f, 7.4559f, 20.1235f, 7.2131f, 20.2241f)
                    curveTo(6.9703f, 20.3248f, 6.71f, 20.3766f, 6.4472f, 20.3766f)
                    curveTo(6.1844f, 20.3766f, 5.9241f, 20.3248f, 5.6813f, 20.2241f)
                    curveTo(5.4385f, 20.1235f, 5.2179f, 19.976f, 5.0322f, 19.79f)
                    curveTo(4.8462f, 19.6043f, 4.6987f, 19.3837f, 4.5981f, 19.1409f)
                    curveTo(4.4974f, 18.8981f, 4.4456f, 18.6378f, 4.4456f, 18.375f)
                    curveTo(4.4456f, 18.1122f, 4.4974f, 17.8519f, 4.5981f, 17.6091f)
                    curveTo(4.6987f, 17.3663f, 4.8462f, 17.1457f, 5.0322f, 16.96f)
                    lineTo(5.0922f, 16.9f)
                    curveTo(5.3227f, 16.6643f, 5.4774f, 16.365f, 5.5362f, 16.0406f)
                    curveTo(5.595f, 15.7162f, 5.5553f, 15.3816f, 5.4222f, 15.08f)
                    curveTo(5.2954f, 14.7842f, 5.0849f, 14.532f, 4.8166f, 14.3543f)
                    curveTo(4.5484f, 14.1766f, 4.234f, 14.0813f, 3.9122f, 14.08f)
                    horizontalLineTo(3.7422f)
                    curveTo(3.2118f, 14.08f, 2.703f, 13.8693f, 2.328f, 13.4942f)
                    curveTo(1.9529f, 13.1191f, 1.7422f, 12.6104f, 1.7422f, 12.08f)
                    curveTo(1.7422f, 11.5496f, 1.9529f, 11.0409f, 2.328f, 10.6658f)
                    curveTo(2.703f, 10.2907f, 3.2118f, 10.08f, 3.7422f, 10.08f)
                    horizontalLineTo(3.8322f)
                    curveTo(4.1632f, 10.0723f, 4.4842f, 9.9651f, 4.7535f, 9.7725f)
                    curveTo(5.0228f, 9.5799f, 5.2279f, 9.3107f, 5.3422f, 9.0f)
                    curveTo(5.4753f, 8.6984f, 5.515f, 8.3638f, 5.4562f, 8.0394f)
                    curveTo(5.3974f, 7.715f, 5.2427f, 7.4157f, 5.0122f, 7.18f)
                    lineTo(4.9522f, 7.12f)
                    curveTo(4.7662f, 6.9342f, 4.6187f, 6.7137f, 4.5181f, 6.4709f)
                    curveTo(4.4174f, 6.2281f, 4.3656f, 5.9678f, 4.3656f, 5.705f)
                    curveTo(4.3656f, 5.4422f, 4.4174f, 5.1819f, 4.5181f, 4.9391f)
                    curveTo(4.6187f, 4.6963f, 4.7662f, 4.4757f, 4.9522f, 4.29f)
                    curveTo(5.1379f, 4.1041f, 5.3585f, 3.9565f, 5.6013f, 3.8559f)
                    curveTo(5.8441f, 3.7552f, 6.1044f, 3.7034f, 6.3672f, 3.7034f)
                    curveTo(6.63f, 3.7034f, 6.8903f, 3.7552f, 7.1331f, 3.8559f)
                    curveTo(7.3759f, 3.9565f, 7.5964f, 4.1041f, 7.7822f, 4.29f)
                    lineTo(7.8422f, 4.35f)
                    curveTo(8.0779f, 4.5805f, 8.3772f, 4.7352f, 8.7016f, 4.794f)
                    curveTo(9.026f, 4.8528f, 9.3606f, 4.8131f, 9.6622f, 4.68f)
                    horizontalLineTo(9.7422f)
                    curveTo(10.038f, 4.5532f, 10.2902f, 4.3428f, 10.4679f, 4.0745f)
                    curveTo(10.6456f, 3.8062f, 10.7409f, 3.4918f, 10.7422f, 3.17f)
                    verticalLineTo(3.0f)
                    curveTo(10.7422f, 2.4696f, 10.9529f, 1.9609f, 11.328f, 1.5858f)
                    curveTo(11.703f, 1.2107f, 12.2118f, 1.0f, 12.7422f, 1.0f)
                    curveTo(13.2726f, 1.0f, 13.7813f, 1.2107f, 14.1564f, 1.5858f)
                    curveTo(14.5315f, 1.9609f, 14.7422f, 2.4696f, 14.7422f, 3.0f)
                    verticalLineTo(3.09f)
                    curveTo(14.7435f, 3.4118f, 14.8388f, 3.7262f, 15.0165f, 3.9945f)
                    curveTo(15.1942f, 4.2628f, 15.4464f, 4.4732f, 15.7422f, 4.6f)
                    curveTo(16.0438f, 4.7331f, 16.3784f, 4.7728f, 16.7028f, 4.714f)
                    curveTo(17.0272f, 4.6552f, 17.3265f, 4.5005f, 17.5622f, 4.27f)
                    lineTo(17.6222f, 4.21f)
                    curveTo(17.8079f, 4.0241f, 18.0285f, 3.8765f, 18.2713f, 3.7759f)
                    curveTo(18.5141f, 3.6752f, 18.7744f, 3.6234f, 19.0372f, 3.6234f)
                    curveTo(19.3f, 3.6234f, 19.5603f, 3.6752f, 19.8031f, 3.7759f)
                    curveTo(20.0459f, 3.8765f, 20.2664f, 4.0241f, 20.4522f, 4.21f)
                    curveTo(20.6381f, 4.3958f, 20.7857f, 4.6163f, 20.8863f, 4.8591f)
                    curveTo(20.987f, 5.1019f, 21.0388f, 5.3622f, 21.0388f, 5.625f)
                    curveTo(21.0388f, 5.8878f, 20.987f, 6.1481f, 20.8863f, 6.3909f)
                    curveTo(20.7857f, 6.6337f, 20.6381f, 6.8542f, 20.4522f, 7.04f)
                    lineTo(20.3922f, 7.1f)
                    curveTo(20.1617f, 7.3357f, 20.007f, 7.635f, 19.9482f, 7.9594f)
                    curveTo(19.8894f, 8.2838f, 19.9291f, 8.6184f, 20.0622f, 8.92f)
                    verticalLineTo(9.0f)
                    curveTo(20.189f, 9.2958f, 20.3994f, 9.548f, 20.6677f, 9.7257f)
                    curveTo(20.936f, 9.9034f, 21.2504f, 9.9987f, 21.5722f, 10.0f)
                    horizontalLineTo(21.7422f)
                    curveTo(22.2726f, 10.0f, 22.7813f, 10.2107f, 23.1564f, 10.5858f)
                    curveTo(23.5315f, 10.9609f, 23.7422f, 11.4696f, 23.7422f, 12.0f)
                    curveTo(23.7422f, 12.5304f, 23.5315f, 13.0391f, 23.1564f, 13.4142f)
                    curveTo(22.7813f, 13.7893f, 22.2726f, 14.0f, 21.7422f, 14.0f)
                    horizontalLineTo(21.6522f)
                    curveTo(21.3304f, 14.0013f, 21.016f, 14.0966f, 20.7477f, 14.2743f)
                    curveTo(20.4794f, 14.452f, 20.2689f, 14.7042f, 20.1422f, 15.0f)
                    close()
                }
            }
        }.build()
        return _settings!!
    }

private var _settings: ImageVector? = null

@Preview
@Composable
private fun IconPreview() {
    Image(imageVector = Icons.Settings, contentDescription = null)
}
