package com.project.design_system.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.project.design_system.theme.SoloRecipeColor
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

private val stepItemHeight = 70.dp

@Composable
fun SoloRecipeStepItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    content: @Composable () -> Unit
) {
    Row(modifier = modifier.height(stepItemHeight)) {
        GlideImage(
            imageModel = { imageUrl },
            modifier = Modifier
                .width(80.dp)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(8.dp)),
            imageOptions = ImageOptions(contentScale = ContentScale.Crop)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = modifier
                .fillMaxHeight()
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = SoloRecipeColor.Secondary10
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(10.dp)
        ) {
            content()
        }
    }
}