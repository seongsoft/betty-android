package io.github.cbinarycastle.betty.ui.theme

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import io.github.cbinarycastle.betty.ui.theme.BettyTheme.typography

@Immutable
data class ExtendedColors(
    val win: Color,
    val draw: Color,
    val lose: Color,
)

@Immutable
data class ExtendedTypography(val appBarTitle: TextStyle)

private val LightColors = lightColors(
    primary = blue600,
    primaryVariant = blue700,
    secondary = yellow,
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
val LocalExtendedTypography = compositionLocalOf { extendedTypography }

@Composable
fun BettyTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalExtendedColors provides LightExtendedColors,
        LocalExtendedTypography provides extendedTypography
    ) {
        MaterialTheme(
            colors = LightColors,
            typography = typography,
            content = content
        )
    }
}

object BettyTheme {

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

    val extendedTypography: ExtendedTypography
        @Composable
        get() = LocalExtendedTypography.current
}