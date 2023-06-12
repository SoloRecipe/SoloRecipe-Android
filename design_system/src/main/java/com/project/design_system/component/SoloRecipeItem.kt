package com.project.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.design_system.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SoloRecipeItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    content: @Composable () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        GlideImage(
            modifier = modifier
                .size(
                    width = 145.dp,
                    height = 120.dp
                )
                .clip(RoundedCornerShape(8.dp)),
            imageModel = { imageUrl },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            failure = {
                Image(
                    painter = painterResource(id = R.drawable.title_image),
                    contentDescription = "title_image",
                    contentScale = ContentScale.Crop
                )
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        content()
    }
}