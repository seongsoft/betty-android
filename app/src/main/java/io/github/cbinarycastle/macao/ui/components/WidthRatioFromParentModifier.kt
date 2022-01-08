package io.github.cbinarycastle.macao.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import kotlin.math.roundToInt

fun Modifier.widthRatioFromParent(ratio: Float) = layout { measurable, constraints ->
    val width = (constraints.maxWidth * ratio).roundToInt()
    val placeable = measurable.measure(constraints.copy(width))
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(0, 0)
    }
}