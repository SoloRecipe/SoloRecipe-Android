package com.project.presentation.view.registration

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeButton
import com.project.design_system.theme.Body2
import com.project.design_system.theme.Body3
import com.project.design_system.theme.IcCamera
import com.project.design_system.theme.SoloRecipeColor
import com.project.design_system.theme.SoloRecipeTypography
import com.project.domain.model.recipe.request.RecipeRequestModel
import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.presentation.viewmodel.registration.RegistrationViewModel
import com.project.presentation.viewmodel.util.UiState
import com.project.presentation.viewmodel.util.changeToPartList
import com.project.presentation.viewmodel.util.getFileFromUri
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import okhttp3.MultipartBody

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    registrationViewModel: RegistrationViewModel = hiltViewModel(),
    navigateToMain: () -> Unit,
    navigateToPrevious: () -> Unit
) {
    val recipeProcess: MutableList<RecipeRequestModel> = mutableListOf()

    val createUiState by registrationViewModel.createUiState.collectAsStateWithLifecycle()
    val modifyUiState by registrationViewModel.modifyUiState.collectAsStateWithLifecycle()
    val recipeImages = registrationViewModel.recipeImages
    val recipeTexts = registrationViewModel.recipeTexts

    var step by remember { mutableStateOf(1) }
    var title by remember { mutableStateOf("") }

    when (createUiState) {
        UiState.Loading -> {}
        is UiState.Success -> { navigateToMain() }
        UiState.BadRequest -> {}
        UiState.Unauthorized -> {}
        else -> {}
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SoloRecipeColor.White)
            .systemBarsPadding()
    ) {
        SoloRecipeAppBar { navigateToPrevious() }
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = modifier.height(16.dp))
            Thumbnail(
                image = when (modifyUiState) {
                    is UiState.Success -> {
                        recipeImages[0]
                    }
                    else -> ""
                },
                imageUpload = { file -> registrationViewModel.imageUpload(file, 0) }
            )
            Spacer(modifier = modifier.height(9.dp))
            ThumbnailTitle(title = title) {
                title = it
                recipeTexts[0] = it
            }
            Spacer(modifier = modifier.height(30.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(step - 1) {
                    StepItem(
                        image = when (modifyUiState) {
                            is UiState.Success -> {
                                recipeImages[it + 1]
                            }
                            else -> ""
                        },
                        imageUpload = { file -> registrationViewModel.imageUpload(file, it + 1) },
                        textUpload = { text -> recipeTexts[it + 1] = text }
                    )
                }
            }
            Spacer(modifier = modifier.height(25.dp))
            RecipeAddButton(
                name = "",
                thumbnail = "",
                recipeProcess = recipeProcess,
                onClick = { step++ },
                addList = {
                    recipeImages.add("")
                    recipeTexts.add("")
                }
            )
            Spacer(modifier = modifier.height(50.dp))
            RecipeRegisterButton {
                val temp = recipeImages.zip(recipeTexts).map { (image, text) ->
                    RecipeRequestModel(
                        image = image,
                        description = text
                    )
                }
                recipeProcess.addAll(temp)
                registrationViewModel.createRecipe(
                    RecipesRequestModel(
                        name = recipeProcess.toList()[0].description,
                        thumbnail = recipeProcess.toList()[0].image,
                        recipeProcess = recipeProcess.apply {
                            removeFirst()
                        }.toList()
                    )
                )
            }
            Spacer(modifier = modifier.height(30.dp))
        }
    }
}

@Composable
fun Thumbnail(
    modifier: Modifier = Modifier,
    image: String,
    imageUpload: (List<MultipartBody.Part>) -> Unit
) {
    val context = LocalContext.current
    val registrationImageUri = remember { mutableStateOf(Uri.EMPTY) }
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        registrationImageUri.value = it
        val file = getFileFromUri(context, registrationImageUri.value)
        file?.let {
            val partList = changeToPartList(file)
            imageUpload(partList)
        }
    }
    var clicked by remember { mutableStateOf(false) }

    GlideImage(
        imageModel = { image.ifEmpty { registrationImageUri.value } },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
            .height(250.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { galleryLauncher.launch("image/*") }
            ),
        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
        failure = {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(
                        color = SoloRecipeColor.Secondary10,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            galleryLauncher.launch("image/*")
                            clicked = !clicked
                        }
                    )
            ) {
                IcCamera(
                    modifier = modifier
                        .size(40.dp, 32.dp)
                        .align(Center),
                    contentDescription = "camera"
                )
            }
        }
    )
}

@Composable
fun StepItem(
    modifier: Modifier = Modifier,
    image: String,
    text: String? = null,
    textUpload: (String) -> Unit,
    imageUpload: (List<MultipartBody.Part>) -> Unit
) {
    var content by remember { mutableStateOf(text ?: "") }
    var clicked by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val registrationImageUri = remember { mutableStateOf(Uri.EMPTY) }
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        registrationImageUri.value = it
        val file = getFileFromUri(context, registrationImageUri.value)
        file?.let {
            val partList = changeToPartList(file)
            imageUpload(partList)
        }
    }

    Row(modifier = modifier.height(70.dp)) {
        GlideImage(
            imageModel = { image.ifEmpty { registrationImageUri.value } },
            modifier = Modifier
                .width(80.dp)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(8.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { galleryLauncher.launch("image/*") }
                ),
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            failure = {
                Box(
                    modifier = modifier
                        .width(80.dp)
                        .fillMaxHeight()
                        .background(
                            color = SoloRecipeColor.Secondary10,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                galleryLauncher.launch("image/*")
                                clicked = !clicked
                            }
                        )
                ) {
                    IcCamera(
                        modifier = modifier
                            .size(20.dp, 16.dp)
                            .align(Center),
                        contentDescription = "camera"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = modifier
                .fillMaxSize()
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = SoloRecipeColor.Secondary10
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(10.dp)
        ) {
            TextField(
                value = content,
                hint = "레시피를 입력해주세요",
                textStyle = SoloRecipeTypography.body4,
                onValueChanged = {
                    content = it
                    textUpload(it)
                }
            )
        }
    }
}

@Composable
fun ThumbnailTitle(
    modifier: Modifier = Modifier,
    title: String,
    onValueChanged: (String) -> Unit
) {
    var text by remember { mutableStateOf(title) }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        value = text,
        hint = "제목을 입력해주세요",
        textStyle = SoloRecipeTypography.body2,
        onValueChanged = {
            text = it
            onValueChanged(it)
        }
    )
}

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    textColor: Color = SoloRecipeColor.Black,
    textStyle: TextStyle,
    onValueChanged: (String) -> Unit
) {
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
    var text by remember { mutableStateOf(value) }

    BasicTextField(
        value = text,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(SoloRecipeColor.Primary10),
        onValueChange = {
            text = it
            onValueChanged(it)
        },
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    if (text.isEmpty()) {
                        Body2(
                            text = hint,
                            textColor = SoloRecipeColor.Secondary10,
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun RecipeAddButton(
    modifier: Modifier = Modifier,
    name: String,
    thumbnail: String,
    recipeProcess: List<RecipeRequestModel>,
    onClick: (RecipesRequestModel) -> Unit,
    addList: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        colors = ButtonDefaults.buttonColors(SoloRecipeColor.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, SoloRecipeColor.Primary10),
        onClick = {
            addList()
            onClick(
                RecipesRequestModel(
                    name = name,
                    thumbnail = thumbnail,
                    recipeProcess = recipeProcess
                )
            )
        }
    ) {
        Body3(
            text = "추가하기",
            textColor = SoloRecipeColor.Primary10,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RecipeRegisterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    SoloRecipeButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        text = "등록하기",
        containerColor = SoloRecipeColor.Primary10
    ) {
        onClick()
    }
}