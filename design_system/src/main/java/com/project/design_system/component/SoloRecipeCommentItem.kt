package com.project.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.design_system.R
import com.project.design_system.theme.Body4
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SoloRecipeCommentItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    content: String
) {
    Row(modifier = modifier) {
        GlideImage(
            modifier = Modifier
                .size(
                    width = 30.dp,
                    height = 30.dp
                )
                .clip(CircleShape),
            imageModel = { imageUrl },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            failure = {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "title_image",
                    contentScale = ContentScale.Crop
                )
            }
        )
        Spacer(modifier = Modifier.width(18.dp))
        Column(modifier = modifier) {
            Body4(text = title)
            Spacer(modifier = Modifier.height(4.dp))
            Body4(text = content)
        }
    }
}