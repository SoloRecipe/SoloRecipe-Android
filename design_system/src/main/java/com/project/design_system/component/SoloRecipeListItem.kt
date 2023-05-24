package com.project.design_system.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SoloRecipeListItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    failureImage: @Composable () -> Unit,
    space: Dp,
    title: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Row {
        GlideImage(
            imageModel = { imageUrl },
            failure = { failureImage() },
        )
        Spacer(modifier = Modifier.width(space))
        Column(modifier = modifier.weight(1f)) {
            title?.let { title ->
                title()
                Spacer(modifier = Modifier.height(4.dp))
            }
            content()
        }
    }
}