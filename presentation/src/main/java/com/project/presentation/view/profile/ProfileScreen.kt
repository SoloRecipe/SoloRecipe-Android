package com.project.presentation.view.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.design_system.R
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeButton
import com.project.design_system.component.SoloRecipeItem
import com.project.design_system.theme.Body2
import com.project.design_system.theme.Body4
import com.project.design_system.theme.IcPencil
import com.project.design_system.theme.SoloRecipeColor
import com.project.domain.model.profile.request.ProfileRequestModel
import com.project.domain.model.profile.response.ProfileResponseModel
import com.project.presentation.viewmodel.profile.ProfileViewModel
import com.project.presentation.viewmodel.util.changeToPartList
import com.project.presentation.viewmodel.util.getPathFromUri
import okhttp3.MultipartBody
import java.io.File
import com.project.presentation.viewmodel.util.UiState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit,
    navigateToDetail: (Long) -> Unit,
    navigateToRegistration: (Long) -> Unit,
    navigateToPrevious: () -> Unit
) {
    LaunchedEffect(Unit) {
        profileViewModel.getUserInfo()
    }

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

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(SoloRecipeColor.White)
                    .systemBarsPadding()
            ) {
                SoloRecipeAppBar { navigateToPrevious() }
                Divider(
                    modifier = modifier.fillMaxWidth(),
                    color = SoloRecipeColor.Secondary10,
                    thickness = 1.dp
                )
                UserInfo(
                    nickname = nickname,
                    onNicknameChanged = { nickname = it },
                    changeNickname = profileViewModel::renameUserName,
                    imageUpload = profileViewModel::imageUpload
                )
                Divider(
                    modifier = modifier.fillMaxWidth(),
                    color = SoloRecipeColor.Secondary20,
                    thickness = 1.dp
                )
                Spacer(modifier = modifier.height(35.dp))
                MyRecipeList(
                    myRecipes = state.data?.myRecipe,
                    navigateToRegistration = navigateToRegistration
                )
                Spacer(modifier = modifier.height(18.dp))
                LikedRecipeList(
                    likedRecipes = state.data?.likeRecipe,
                    navigateToDetail = navigateToDetail
                )
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
    onNicknameChanged: (String) -> Unit,
    changeNickname: (ProfileRequestModel) -> Unit,
    imageUpload: (List<MultipartBody.Part>) -> Unit
) {
    val context = LocalContext.current
    val profileImageUri = remember { mutableStateOf(Uri.EMPTY) }
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        profileImageUri.value = it
        val file = File(getPathFromUri(context, profileImageUri.value))
        val partList = changeToPartList(file)
        imageUpload(partList)
    }
    
    var isReadOnly by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 17.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        GlideImage(
            imageModel = {
                if (profileImageUri.value.toString() != "") profileImageUri.value
                else R.drawable.ic_profile
            },
            modifier = Modifier
                .size(85.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { galleryLauncher.launch("image/*") }
                ),
            imageOptions = ImageOptions(contentScale = ContentScale.Crop)
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
    navigateToRegistration: (Long) -> Unit
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
                val idx = myRecipes!![it].idx

                SoloRecipeItem(
                    modifier = modifier.clickable { navigateToRegistration(idx) },
                    imageUrl = myRecipes[it].thumbnail,
                    content = { Body4(text = myRecipes[it].name) }
                )
            }
        }
    }
}

@Composable
fun LikedRecipeList(
    modifier: Modifier = Modifier,
    likedRecipes: List<ProfileResponseModel>?,
    navigateToDetail: (Long) -> Unit,
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
                val idx = likedRecipes!![it].idx

                SoloRecipeItem(
                    modifier = modifier.clickable { navigateToDetail(idx) },
                    imageUrl = likedRecipes[it].thumbnail,
                    content = { Body4(text = likedRecipes[it].name) }
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
