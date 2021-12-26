package io.github.cbinarycastle.macao.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

private val LightColors = lightColors(
    primary = blue700,
    primaryVariant = blue800,
    secondary = amber500
)

private val darkColors = darkColors(
    primary = blue300
)

@Composable
fun MacaoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) = MaterialTheme(
    colors = LightColors,
    content = content
)

object MacaoTheme {

    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes
}