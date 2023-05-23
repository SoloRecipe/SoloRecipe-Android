package com.project.design_system.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun SoloRecipeTheme(
    color: SoloRecipeColor = SoloRecipeTheme.color,
    typography: SoloRecipeTypography = SoloRecipeTheme.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColor provides color,
        LocalTypography provides typography
    ) {
        content()
    }
}

object SoloRecipeTheme {
    val color: SoloRecipeColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current
    val typography: SoloRecipeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}