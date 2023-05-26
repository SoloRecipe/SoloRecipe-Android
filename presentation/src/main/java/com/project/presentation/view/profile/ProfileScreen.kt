package com.project.presentation.view.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeButton
import com.project.design_system.component.SoloRecipeItem
import com.project.design_system.theme.Body2
import com.project.design_system.theme.IcProfile
import com.project.design_system.theme.SoloRecipeColor

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SoloRecipeColor.White)
    ) {
        ProfileHeader()
        Divider(
            modifier = modifier.fillMaxWidth(),
            color = SoloRecipeColor.Secondary10,
            thickness = 1.dp
        )
        Spacer(modifier = modifier.height(30.dp))
        UserImage { }
        Spacer(modifier = modifier.height(17.dp))
        NickName()
        Spacer(modifier = modifier.height(30.dp))
        Divider(
            modifier = modifier.fillMaxWidth(),
            color = SoloRecipeColor.Secondary20,
            thickness = 1.dp
        )
        Spacer(modifier = modifier.height(35.dp))
        WriteRecipe()
        Spacer(modifier = modifier.height(18.dp))
        LikeRecipe()
        Spacer(modifier = modifier.height(38.dp))
        Divider(
            modifier = modifier.fillMaxWidth(),
            color = SoloRecipeColor.Secondary20,
            thickness = 1.dp
        )
        Spacer(modifier = modifier.height(40.dp))
        LogoutButton()
        Spacer(modifier = modifier.weight(1f))
    }
}

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        SoloRecipeAppBar { }
    }
}

@Composable
fun UserImage(
    modifier: Modifier = Modifier,
    changeImage: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        IcProfile(
            modifier = modifier
                .align(Alignment.Center)
                .size(85.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { changeImage() }
                ),
            contentDescription = "user image"
        )
    }
}

@Composable
fun NickName(
    modifier: Modifier = Modifier,
    nickName: String = "닉네임"
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Body2(
            modifier = modifier.align(Alignment.Center),
            text = nickName
        )
    }
}

@Composable
fun WriteRecipe(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {
        Body2(text = "작성한 레시피")
        Spacer(modifier = modifier.height(30.dp))
        LazyRow {
            items(5) {
                SoloRecipeItem(
                    imageUrl = "https://src.hidoc.co.kr/image/lib/2020/11/9/1604911318873_0",
                    recipeName = "피자"
                )
            }
        }
    }
}

@Composable
fun LikeRecipe(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {
        Body2(text = "좋아요한 레시피")
        Spacer(modifier = modifier.height(30.dp))
        LazyRow {
            items(5) {
                SoloRecipeItem(
                    imageUrl = "https://pixabay.com/ko/photos/%ed%8c%ac%ec%bc%80%ec%9d%b4%ed%81%ac-%eb%a9%94%ec%9d%b4%ed%94%8c-%ec%8b%9c%eb%9f%bd-%eb%9d%bc%ec%a6%88%eb%b2%a0%eb%a6%ac-2291908/",
                    recipeName = "피자"
                )
            }
        }
    }
}

@Composable
fun LogoutButton(modifier: Modifier = Modifier) {
    SoloRecipeButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
            .height(50.dp),
        text = "로그아웃",
        containerColor = SoloRecipeColor.Secondary30
    ) {}
}

@Preview
@Composable
fun previewProfileScreen() {
    ProfileScreen()
}