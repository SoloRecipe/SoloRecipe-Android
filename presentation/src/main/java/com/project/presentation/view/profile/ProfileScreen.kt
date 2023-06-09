package com.project.presentation.view.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeButton
import com.project.design_system.component.SoloRecipeItem
import com.project.design_system.theme.Body2
import com.project.design_system.theme.Body4
import com.project.design_system.theme.IcPencil
import com.project.design_system.theme.IcProfile
import com.project.design_system.theme.SoloRecipeColor
import com.project.domain.model.profile.request.ProfileImageRequestModel
import com.project.domain.model.profile.request.ProfileRequestModel
import com.project.domain.model.profile.response.ProfileResponseModel
import com.project.presentation.viewmodel.profile.ProfileViewModel
import com.project.presentation.viewmodel.util.UiState

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit
) {
    val userUiState by profileViewModel.userUiState.collectAsStateWithLifecycle()
    val deleteUiState by profileViewModel.deleteUiState.collectAsStateWithLifecycle()

    when (deleteUiState) {
        UiState.Loading -> {}
        is UiState.Success -> { navigateToSignIn() }
        UiState.Unauthorized -> {}
        UiState.NotFound -> {}
        else -> {}
    }

    when (val state = userUiState) {
        UiState.Loading -> {}
        is UiState.Success -> {
            var nickname by remember { mutableStateOf(state.data?.name ?: "") }

            LaunchedEffect(Unit) {
                profileViewModel.getUserInfo()
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(SoloRecipeColor.White)
            ) {
                SoloRecipeAppBar { }
                Divider(
                    modifier = modifier.fillMaxWidth(),
                    color = SoloRecipeColor.Secondary10,
                    thickness = 1.dp
                )
                UserInfo(
                    nickname = nickname,
                    onNicknameChanged = { nickname = it },
                    changeNickname = profileViewModel::renameUserName,
                    changeImage = profileViewModel::modifyProfileImage
                )
                Divider(
                    modifier = modifier.fillMaxWidth(),
                    color = SoloRecipeColor.Secondary20,
                    thickness = 1.dp
                )
                Spacer(modifier = modifier.height(35.dp))
                MyRecipeList(myRecipes = state.data?.myRecipe)
                Spacer(modifier = modifier.height(18.dp))
                LikedRecipeList(likedRecipes = state.data?.likeRecipe)
                Spacer(modifier = modifier.height(38.dp))
                Divider(
                    modifier = modifier.fillMaxWidth(),
                    color = SoloRecipeColor.Secondary20,
                    thickness = 1.dp
                )
                Spacer(modifier = modifier.height(40.dp))
                LogoutButton(logout = profileViewModel::deleteUserInfo)
                Spacer(modifier = modifier.weight(1f))
            }
        }
        UiState.Unauthorized -> {}
        else -> {}
    }
}

@Composable
fun UserInfo(
    modifier: Modifier = Modifier,
    nickname: String,
    profileImg: String = "",
    onNicknameChanged: (String) -> Unit,
    changeNickname: (ProfileRequestModel) -> Unit,
    changeImage: (ProfileImageRequestModel) -> Unit
) {
    var isReadOnly by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 17.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        IcProfile(
            modifier = modifier
                .size(85.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { changeImage(ProfileImageRequestModel(profileImg = profileImg)) }
                ),
            contentDescription = "user image"
        )
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = nickname,
            onValueChange = { onNicknameChanged(it) },
            keyboardActions = KeyboardActions(onDone = {
                changeNickname(ProfileRequestModel(name = nickname))
            }),
            readOnly = isReadOnly
        )
        IcPencil(
            contentDescription = "rename",
            modifier = modifier.clickable { isReadOnly = !isReadOnly }
        )
    }
}

@Composable
fun MyRecipeList(
    modifier: Modifier = Modifier,
    myRecipes: List<ProfileResponseModel>?,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {
        Body2(text = "작성한 레시피")
        Spacer(modifier = modifier.height(30.dp))
        LazyRow {
            items(myRecipes?.size ?: 0) {
                SoloRecipeItem(
                    imageUrl = myRecipes?.get(it)?.thumbnail ?: "",
                    content = { Body4(text = myRecipes?.get(it)?.name ?: "") }
                )
            }
        }
    }
}

@Composable
fun LikedRecipeList(
    modifier: Modifier = Modifier,
    likedRecipes: List<ProfileResponseModel>?
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {
        Body2(text = "좋아요한 레시피")
        Spacer(modifier = modifier.height(30.dp))
        LazyRow {
            items(likedRecipes?.size ?: 0) {
                SoloRecipeItem(
                    imageUrl = likedRecipes?.get(it)?.thumbnail ?: "",
                    content = { Body4(text = likedRecipes?.get(it)?.name ?: "") }
                )
            }
        }
    }
}

@Composable
fun LogoutButton(
    modifier: Modifier = Modifier,
    logout: () -> Unit
) {
    SoloRecipeButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        text = "로그아웃",
        containerColor = SoloRecipeColor.Secondary30
    ) { logout() }
}