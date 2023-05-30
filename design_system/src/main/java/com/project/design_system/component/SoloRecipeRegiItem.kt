package com.project.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.project.design_system.R
import com.project.design_system.theme.Body4
import com.project.design_system.theme.SoloRecipeColor
import com.project.design_system.theme.SoloRecipeTypography.body4

@Composable
fun SoloRecipeRegiItem(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    image: Painter? = null,
    onValueChanged: (String) -> Unit
) {
    Row(modifier = Modifier.height(70.dp)) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .fillMaxHeight()
                .background(
                    color = if (image == null) SoloRecipeColor.Secondary10 else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Image(
                modifier = modifier.align(Alignment.Center),
                painter = image ?: painterResource(id = R.drawable.ic_camera),
                contentDescription = "image",
                contentScale = if (image == null) ContentScale.None else ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        BasicTextField(
            modifier = modifier
                .fillMaxHeight()
                .border(
                    width = 1.dp,
                    color = SoloRecipeColor.Secondary10,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(10.dp),
            cursorBrush = SolidColor(SoloRecipeColor.Primary10),
            value = value,
            textStyle = body4.merge(TextStyle(color = SoloRecipeColor.Black)),
            onValueChange = onValueChanged,
            decorationBox = { innerTextField ->
                Box(modifier = modifier) {
                    if (value.isEmpty()) {
                        Body4(
                            text = hint,
                            textColor = SoloRecipeColor.Secondary10,
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}