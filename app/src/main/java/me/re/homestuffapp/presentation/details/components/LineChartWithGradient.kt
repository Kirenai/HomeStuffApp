package me.re.homestuffapp.presentation.details.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LineChartWithGradient(
    modifier: Modifier = Modifier,
    data: List<Float>,
    xLabels: List<String>,
    lineColor: Color = MaterialTheme.colorScheme.primary,
    gradientColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(148.dp)
        ) {
            if (data.isEmpty()) return@Canvas

            val width = size.width // 300
            val height = size.height // 400

            val maxValue = data.maxOrNull() ?: 0f // 1.4f
            val minValue = data.minOrNull() ?: 0f // 1.3f
            val range = maxValue - minValue // 1.4f - 1.3f = 0.1f

            val spacing = width / (data.size - 1) // 300 / (2 - 1) = 300

            val points = data.mapIndexed { i, value ->
                val x = i * spacing // 0 * 300 = 0, 1 * 300 = 300
                val y = height - ((value - minValue) / range * height) // 400 - ((1.3f - 1.3f) / 0.1f * 400) = 400, 400 - ((1.4f - 1.3f) / 0.1f * 400) = 0
                Offset(x, y)
            }

            val smoothPath = Path().apply {
                moveTo(points.first().x, points.first().y)
                for (i in 1 until points.size) {
                    val prev = points[i - 1] // 0, 300
                    val curr = points[i] // 300, 0
                    val midPoint = Offset((prev.x + curr.x) / 2, (prev.y + curr.y) / 2)
                    quadraticTo(prev.x, prev.y, midPoint.x, midPoint.y)
                }
                lineTo(points.last().x, points.last().y)
            }

            val gradientPath = Path().apply {
                addPath(smoothPath)
                lineTo(points.last().x, height)
                lineTo(points.first().x, height)
                close()
            }

            drawPath(
                path = gradientPath,
                brush = Brush.verticalGradient(
                    colors = listOf(gradientColor, Color.Transparent),
                    endY = height
                )
            )

            drawPath(
                path = smoothPath,
                color = lineColor,
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            xLabels.forEach { label ->
                Text(
                    text = label,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LineChartWithGradientPreview() {
    val sampleData = listOf(1.3f, 1.4f)
    val xLabels = listOf("Jan", "Feb")

    LineChartWithGradient(
        data = sampleData,
        xLabels = xLabels,
        modifier = Modifier.padding(16.dp)
    )
}