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
    val surfaceVariant: Color,
    val success: Color,
    val neutral: Color,
)

private val LightColors = lightColors(
    primary = blue600,
    primaryVariant = blue700,
    secondary = amber500,
)

private val DarkColors = darkColors(
    primary = blue200
)

private val LightExtendedColors = ExtendedColors(
    surfaceVariant = gray200,
    success = green800,
    neutral = gray600,
)

private val DarkExtendedColors = ExtendedColors(
    surfaceVariant = gray800,
    success = green200,
    neutral = gray300,
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