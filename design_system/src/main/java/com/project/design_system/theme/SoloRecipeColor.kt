package com.project.design_system.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object SoloRecipeColor {
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)

    val Primary10 = Color(0xFFA0BDEB)
    val Primary20 = Color(0xFFC2D2EB)

    val Secondary10 = Color(0xFFD9D9D9)
    val Secondary20 = Color(0xFFBBBBCC)
    val Secondary30 = Color(0xFFFF5D5D)
}

internal val LocalColor = staticCompositionLocalOf { SoloRecipeColor }