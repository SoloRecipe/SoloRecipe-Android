package com.project.presentation.viewmodel.modify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.domain.model.recipe.response.RecipeDetailResponseModel
import com.project.domain.usecase.image.ImageUploadUseCase
import com.project.domain.usecase.recipe.DeleteRecipeUseCase
import com.project.domain.usecase.recipe.GetRecipeDetailUseCase
import com.project.domain.usecase.recipe.ModifyRecipeUseCase
import com.project.presentation.viewmodel.util.UiState
import com.project.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ModifyViewModel @Inject constructor(
    private val modifyRecipeUseCase: ModifyRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase,
    private val imageUploadUseCase: ImageUploadUseCase
) : ViewModel() {
    private val _recipeUiState: MutableStateFlow<UiState<RecipeDetailResponseModel>> = MutableStateFlow(UiState.Loading)
    val recipeUiState = _recipeUiState.asStateFlow()

    val recipeImages = mutableListOf<String>()
    val recipeTexts = mutableListOf<String>()

    fun getRecipeDetail(index: Long) {
        viewModelScope.launch {
            getRecipeDetailUseCase(index)
                .onSuccess { result ->
                    _recipeUiState.value = UiState.Success(result)
                    recipeImages.add(result.thumbnail)
                    recipeTexts.add(result.name)
                    repeat(result.recipeProcess.size) {
                        recipeImages.add(result.recipeProcess[it].image)
                        recipeTexts.add(result.recipeProcess[it].description)
                    }
                }.onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = { _recipeUiState.value = UiState.Unauthorized },
                        notFoundAction = { _recipeUiState.value = UiState.NotFound }
                    )
                }
        }

    }

    fun modifyRecipe(
        index: Long,
        body: RecipesRequestModel
    ) {
        viewModelScope.launch {
            modifyRecipeUseCase(index, body)
                .onSuccess { }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = { },
                        unauthorizedAction = { },
                        forbiddenAction = { },
                        notFoundAction = { }
                    )
                }
        }
    }

    fun deleteRecipe(index: Long) {
        viewModelScope.launch {
            deleteRecipeUseCase(index)
                .onSuccess { }
                .onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = { },
                        forbiddenAction = { },
                        notFoundAction = { }
                    )
                }
        }
    }

    fun imageUpload(file: List<MultipartBody.Part>, index: Int) {
        viewModelScope.launch {
            imageUploadUseCase(file)
                .onSuccess { recipeImages[index] = it.images[0] }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = { },
                        unauthorizedAction = { }
                    )
                }
        }
    }
}