package io.github.cbinarycastle.macao.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val win: Color,
    val draw: Color,
    val lose: Color,
)

private val LightColors = lightColors(
    primary = blue600,
    primaryVariant = blue700,
    secondary = amber500,
    background = gray100,
)

private val DarkColors = darkColors(
    primary = blue200
)

private val LightExtendedColors = ExtendedColors(
    win = lightGreen,
    draw = gray600,
    lose = redA700,
)

private val DarkExtendedColors = ExtendedColors(
    win = darkGreen,
    draw = gray300,
    lose = redA700,
)

val LocalExtendedColors = compositionLocalOf { LightExtendedColors }

@Composable
fun MacaoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalExtendedColors provides if (darkTheme) DarkExtendedColors else LightExtendedColors
    ) {
        MaterialTheme(
            colors = if (darkTheme) DarkColors else LightColors,
            typography = typography,
            content = content
        )
    }
}

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

    val extendedColors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}