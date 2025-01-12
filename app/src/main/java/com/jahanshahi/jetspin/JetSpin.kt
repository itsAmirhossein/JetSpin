package com.jahanshahi.jetspin

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun JetSpin() {
    val startTime = remember { System.currentTimeMillis() }
    TimelineComposable() { currentTime ->
        val elapsedTime = (currentTime - startTime) / 60000f
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val rotation = 0.8f + 0.2f * abs((cos(PI * elapsedTime.toDouble()) ) / 2.0).toFloat()
            val radius = 1.dp.toPx()
            val center = Offset(x = size.width / 2 - radius, y = size.height / 2 - radius)
            val spiralIterations = 3000
            for (i in 0 until spiralIterations) {
                val j = rotation * i
                val p = spiral(j) / 2f
                val hue = j % 255
                drawCircle(
                    color = Color.hsv(hue,1.0f,1.0f),
                    radius = radius,
                    center = Offset(center.x + p.x, center.y + p.y)
                )
            }
        }
    }
}

fun spiral(theta: Float): Offset {
    val r = Math.toRadians(60.0* theta.toDouble()).toFloat()
    return Offset(r * cos(theta), r * sin(theta))
}