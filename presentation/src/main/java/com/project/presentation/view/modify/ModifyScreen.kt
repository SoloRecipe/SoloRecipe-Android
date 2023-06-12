package com.project.presentation.view.modify

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.project.design_system.theme.IcTrashcan
import com.project.design_system.theme.SoloRecipeColor
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
    navigateToPrevious: () -> Unit
) {
    LaunchedEffect(Unit) {
        modifyViewModel.getRecipeDetail(checkNotNull(index))
    }

    val recipeProcess: List<RecipeRequestModel> = listOf()

    val modifyUiState by modifyViewModel.modifyUiState.collectAsStateWithLifecycle()
    val deleteUiState by modifyViewModel.deleteRecipeUiState.collectAsStateWithLifecycle()
    val recipeUiState by modifyViewModel.recipeUiState.collectAsStateWithLifecycle()

    when (val state = recipeUiState) {
        UiState.Loading -> {}
        is UiState.Success -> {
            var step by remember { mutableStateOf(5) }
            var title by remember { mutableStateOf(state.data?.name ?: "") }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(SoloRecipeColor.White)
                    .systemBarsPadding()
            ) {
                SoloRecipeAppBar(
                    endIcons = {
                        IcTrashcan(
                            modifier = modifier.clickable { modifyViewModel.deleteRecipe(checkNotNull(index)) },
                            contentDescription = "delete"
                        )
                    }
                ) {
                    navigateToPrevious()
                }
                Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                    Spacer(modifier = modifier.height(16.dp))
                    Thumbnail(imageUpload = modifyViewModel::imageUpload, image = state.data?.thumbnail)
                    Spacer(modifier = modifier.height(9.dp))
                    ThumbnailTitle(title = title) { title = it }
                    Spacer(modifier = modifier.height(30.dp))
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        val idx = if (step < (state.data?.recipeProcess?.size ?: 0)) step else 0
                        repeat(idx) {
                            StepItem(
                                imageUpload = modifyViewModel::imageUpload,
                                image = state.data?.recipeProcess?.get(it)?.image,
                                text = state.data?.recipeProcess?.get(it)?.description
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(25.dp))
                    RecipeAddButton(
                        name = "",
                        thumbnail = "",
                        recipeProcess = recipeProcess,
                        onClick = { step++ }
                    )
                    Spacer(modifier = modifier.height(50.dp))
                    RecipeModifyButton {
                        modifyViewModel.modifyRecipe(
                            index = checkNotNull(index),
                            body = RecipesRequestModel(
                                name = title,
                                thumbnail = state.data!!.thumbnail,
                                recipeProcess = recipeProcess
                            )
                        )
                    }
                    Spacer(modifier = modifier.height(30.dp))
                }
            }
        }
        UiState.Unauthorized -> {}
        UiState.NotFound -> {}
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
