package com.project.presentation.view.modify

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeButton
import com.project.design_system.component.SoloRecipeDialog
import com.project.design_system.theme.IcTrashcan
import com.project.design_system.theme.SoloRecipeColor
import com.project.design_system.theme.SoloRecipeTheme
import com.project.domain.model.recipe.request.RecipeRequestModel
import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.presentation.view.registration.RecipeAddButton
import com.project.presentation.view.registration.StepItem
import com.project.presentation.view.registration.Thumbnail
import com.project.presentation.view.registration.ThumbnailTitle
import com.project.presentation.viewmodel.modify.ModifyViewModel
import com.project.presentation.viewmodel.util.UiState

@Composable
fun ModifyScreen(
    modifier: Modifier = Modifier,
    index: Long?,
    modifyViewModel: ModifyViewModel = hiltViewModel(),
    navigateToProfile: () -> Unit,
    navigateToPrevious: () -> Unit
) {
    LaunchedEffect(Unit) {
        modifyViewModel.getRecipeDetail(checkNotNull(index))
    }

    val recipeState by modifyViewModel.recipeUiState.collectAsStateWithLifecycle()

    when (val state = recipeState) {
        UiState.Loading -> {}
        is UiState.Success -> {
            val recipeProcess: MutableList<RecipeRequestModel> = mutableListOf()

            val recipeImages = modifyViewModel.recipeImages
            val recipeTexts = modifyViewModel.recipeTexts

            var step by remember { mutableStateOf(state.data?.recipeProcess?.size ?: 1) }
            var title by remember { mutableStateOf(state.data?.name ?: "") }

            var deleteClicked by remember { mutableStateOf(false) }
            var modifyClicked by remember { mutableStateOf(false) }

            if (deleteClicked) {
                SoloRecipeDialog(
                    title = "레시피 삭제",
                    description = "글을 삭제하시면 복구가 불가능합니다.",
                    onDismiss = { deleteClicked = false }) {
                    SoloRecipeButton(
                        modifier = modifier.weight(1f),
                        text = "취소",
                        containerColor = SoloRecipeTheme.color.Primary10
                    ) {
                        deleteClicked = false
                    }
                    Spacer(modifier = modifier.width(10.dp))
                    SoloRecipeButton(
                        modifier = modifier
                            .weight(1f)
                            .border(
                                width = 1.dp,
                                color = SoloRecipeTheme.color.Primary10,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        text = "삭제",
                        textColor = SoloRecipeTheme.color.Black,
                        containerColor = Color.White
                    ) {
                        deleteClicked = false
                        modifyViewModel.deleteRecipe(checkNotNull(index))
                        navigateToProfile()
                    }
                }
            }

            if (modifyClicked) {
                SoloRecipeDialog(
                    title = "레시피 수정",
                    description = "글을 수정하시면 복구가 불가능합니다.",
                    onDismiss = { modifyClicked = false }
                ) {
                    SoloRecipeButton(
                        modifier = modifier.weight(1f),
                        text = "취소",
                        containerColor = SoloRecipeTheme.color.Primary10
                    ) {
                        modifyClicked = false
                    }
                    Spacer(modifier = modifier.width(10.dp))
                    SoloRecipeButton(
                        modifier = modifier
                            .weight(1f)
                            .border(
                                width = 1.dp,
                                color = SoloRecipeTheme.color.Primary10,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        text = "수정",
                        textColor = SoloRecipeTheme.color.Black,
                        containerColor = Color.White
                    ) {
                        modifyClicked = false
                        val temp = recipeImages.zip(recipeTexts).map { (image, text) ->
                            RecipeRequestModel(
                                image = image,
                                description = text
                            )
                        }
                        recipeProcess.addAll(temp)
                        modifyViewModel.modifyRecipe(
                            index = state.data!!.idx,
                            body = RecipesRequestModel(
                                name = recipeTexts[0],
                                thumbnail = recipeImages[0],
                                recipeProcess = recipeProcess.apply {
                                    removeFirst()
                                }.toList()
                            )
                        )
                        navigateToProfile()
                    }
                }
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(SoloRecipeColor.White)
                    .systemBarsPadding()
            ) {
                SoloRecipeAppBar(endIcons = {
                    IcTrashcan(
                        modifier = modifier.clickable { deleteClicked = true },
                        contentDescription = "remove"
                    )
                }) {
                    navigateToPrevious()
                }
                Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                    Spacer(modifier = modifier.height(16.dp))
                    Thumbnail(
                        image = state.data?.thumbnail ?: "",
                        imageUpload = { file -> modifyViewModel.imageUpload(file, 0) }
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
                        repeat(step) {
                            val isExist = it + 1 <= (state.data?.recipeProcess?.size ?: 0)
                            StepItem(
                                image = if (isExist) state.data?.recipeProcess?.get(it)?.image ?: "" else "",
                                text = if (isExist) state.data?.recipeProcess?.get(it)?.description ?: "" else "",
                                imageUpload = { file -> modifyViewModel.imageUpload(file, it + 1) },
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
                    RecipeModifyButton {
                        modifyClicked = true
                    }
                    Spacer(modifier = modifier.height(30.dp))
                }
            }
        }
        UiState.BadRequest -> {}
        UiState.Unauthorized -> {}
        else -> {}
    }
}

@Composable
fun RecipeModifyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    SoloRecipeButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        text = "수정하기",
        containerColor = SoloRecipeColor.Primary10
    ) {
        onClick()
    }
}