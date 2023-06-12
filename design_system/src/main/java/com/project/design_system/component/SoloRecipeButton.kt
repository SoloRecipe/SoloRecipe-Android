package com.project.design_system.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.design_system.theme.Body3
import com.project.design_system.theme.SoloRecipeColor

@Composable
fun SoloRecipeButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SoloRecipeColor.White,
    enabled: Boolean = true,
    containerColor: Color,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = RoundedCornerShape(8.dp),
        contentPadding = contentPadding,
        onClick = { onClick() }
    ) {
        Body3(
            text = text,
            textColor = textColor,
            textAlign = TextAlign.Center
        )
    }
}