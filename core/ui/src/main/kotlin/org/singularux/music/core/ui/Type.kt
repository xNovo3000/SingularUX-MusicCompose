package org.singularux.music.core.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

private val musicFontFamily = FontFamily(
    Font(resId = R.font.noto_sans_regular, weight = FontWeight.Normal),
    Font(resId = R.font.noto_sans_regular_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resId = R.font.noto_sans_medium, weight = FontWeight.Medium),
    Font(resId = R.font.noto_sans_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
)

private val baselineFont = Typography()

internal val MusicTypography = Typography(
    displayLarge = baselineFont.displayLarge.copy(fontFamily = musicFontFamily),
    displayMedium = baselineFont.displayMedium.copy(fontFamily = musicFontFamily),
    displaySmall = baselineFont.displaySmall.copy(fontFamily = musicFontFamily),
    headlineLarge = baselineFont.headlineLarge.copy(fontFamily = musicFontFamily),
    headlineMedium = baselineFont.headlineMedium.copy(fontFamily = musicFontFamily),
    headlineSmall = baselineFont.headlineSmall.copy(fontFamily = musicFontFamily),
    titleLarge = baselineFont.titleLarge.copy(fontFamily = musicFontFamily),
    titleMedium = baselineFont.titleMedium.copy(fontFamily = musicFontFamily),
    titleSmall = baselineFont.titleSmall.copy(fontFamily = musicFontFamily),
    bodyLarge = baselineFont.bodyLarge.copy(fontFamily = musicFontFamily),
    bodyMedium = baselineFont.bodyMedium.copy(fontFamily = musicFontFamily),
    bodySmall = baselineFont.bodySmall.copy(fontFamily = musicFontFamily),
    labelLarge = baselineFont.labelLarge.copy(fontFamily = musicFontFamily),
    labelMedium = baselineFont.labelMedium.copy(fontFamily = musicFontFamily),
    labelSmall = baselineFont.labelSmall.copy(fontFamily = musicFontFamily),
)