package com.example.studentformquiz.ui.theme

import androidx.compose.material3.Typography as MaterialTypography
import androidx.compose.ui.text.font.FontFamily

private val AppFontFamily = FontFamily.Serif
private val BaseTypography = MaterialTypography()

val Typography = BaseTypography.copy(
    bodyLarge = BaseTypography.bodyLarge.copy(fontFamily = AppFontFamily),
    bodyMedium = BaseTypography.bodyMedium.copy(fontFamily = AppFontFamily),
    labelLarge = BaseTypography.labelLarge.copy(fontFamily = AppFontFamily),
    titleLarge = BaseTypography.titleLarge.copy(fontFamily = AppFontFamily)
)
