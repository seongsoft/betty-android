package io.github.cbinarycastle.macao.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import io.github.cbinarycastle.macao.R

val robotoFamily = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_medium, FontWeight.Medium),
)

val typography = Typography(defaultFontFamily = robotoFamily)