package com.timkwali.weatherly.core.presentation.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.layout
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Modifier.circularMotion(
    radius: Float = 150f,
    durationMillis: Int = 4000
): Modifier = composed {
    val angle = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        angle.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    this.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        val xOffset = radius * cos(Math.toRadians(angle.value.toDouble())).toFloat()
        val yOffset = radius * sin(Math.toRadians(angle.value.toDouble())).toFloat()

        val xPos = (constraints.maxWidth / 2) + xOffset - (placeable.width / 2)
        val yPos = (constraints.maxHeight / 2) + yOffset - (placeable.height / 2)

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeable.placeRelative(xPos.toInt(), yPos.toInt())
        }
    }
}