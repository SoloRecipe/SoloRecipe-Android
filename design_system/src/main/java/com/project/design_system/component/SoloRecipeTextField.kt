package com.project.design_system.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.project.design_system.theme.Body2
import com.project.design_system.theme.SoloRecipeColor
import com.project.design_system.theme.SoloRecipeTypography

@Composable
fun SoloRecipeTextField(
    modifier: Modifier = Modifier,
    value: String,
    enabled: Boolean = true,
    hint: String,
    textColor: Color = SoloRecipeColor.Black,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    textStyle: TextStyle = SoloRecipeTypography.body2,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    trailingIcon: (@Composable () -> Unit)? = null,
    onValueChanged: (String) -> Unit
) {
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    BasicTextField(
        modifier = modifier
            .drawWithContent {
                drawContent()
                drawLine(
                    color = SoloRecipeColor.Secondary10,
                    start = Offset(
                        x = 0f,
                        y = size.height
                    ),
                    end = Offset(
                        x = size.width,
                        y = size.height
                    )
                )
            }
            .padding(vertical = 12.dp),
        value = value,
        enabled = enabled,
        singleLine = singleLine,
        maxLines = maxLines,
        textStyle = mergedTextStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        cursorBrush = SolidColor(SoloRecipeColor.Primary10),
        onValueChange = onValueChanged,
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    if (value.isEmpty()) {
                        Body2(
                            text = hint,
                            textColor = SoloRecipeColor.Secondary10,
                            maxLines = maxLines
                        )
                    }
                    innerTextField()
                }
                Spacer(modifier = modifier.weight(1f))
                trailingIcon?.let { trailingIcon ->
                    trailingIcon()
                }
            }
        }
    )
}