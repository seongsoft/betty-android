package io.github.cbinarycastle.macao.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.border(
    start: Dp,
    top: Dp,
    end: Dp,
    bottom: Dp,
    color: Color,
): Modifier = drawBehind {
    if (start > 0.dp) {
        drawLine(
            color = color,
            start = Offset(start.toPx(), 0f),
            end = Offset(start.toPx(), size.height),
            strokeWidth = start.toPx()
        )
    }
    if (top > 0.dp) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = top.toPx()
        )
    }
    if (end > 0.dp) {
        drawLine(
            color = color,
            start = Offset(size.width - end.toPx(), 0f),
            end = Offset(size.width - end.toPx(), size.height),
            strokeWidth = end.toPx()
        )
    }
    if (bottom > 0.dp) {
        drawLine(
            color = color,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = bottom.toPx()
        )
    }
}