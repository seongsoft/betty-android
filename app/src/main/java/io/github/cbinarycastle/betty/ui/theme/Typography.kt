package io.github.cbinarycastle.betty.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.cbinarycastle.betty.R

val robotoFamily = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_medium, FontWeight.Medium),
)

val poppinsFamily = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_bold, FontWeight.Bold),
)

val defaultTypography = Typography(
    defaultFontFamily = poppinsFamily,
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        letterSpacing = 0.15.sp
    )
)

val defaultExtendedTypography = ExtendedTypography(
    appBarTitle = TextStyle(
        color = Color.Black,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.15.sp
    )
)