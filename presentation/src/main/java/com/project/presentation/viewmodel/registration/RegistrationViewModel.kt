package com.project.presentation.viewmodel.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.domain.model.recipe.response.RecipeDetailResponseModel
import com.project.domain.usecase.image.ImageUploadUseCase
import com.project.domain.usecase.recipe.CreateRecipeUseCase
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
class RegistrationViewModel @Inject constructor(
    private val createRecipeUseCase: CreateRecipeUseCase,
    private val imageUploadUseCase: ImageUploadUseCase,
    private val modifyRecipeUseCase: ModifyRecipeUseCase,
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {
    private val _createUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val createUiState = _createUiState.asStateFlow()

    private val _modifyUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val modifyUiState = _modifyUiState.asStateFlow()

    private val _recipeUiState: MutableStateFlow<UiState<RecipeDetailResponseModel>> = MutableStateFlow(UiState.Loading)
    val recipeUiState = _recipeUiState
    fun createRecipe(body: RecipesRequestModel) {
        viewModelScope.launch {
            createRecipeUseCase(body)
                .onSuccess {
                    _createUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _createUiState.value = UiState.BadRequest },
                        unauthorizedAction = { _createUiState.value = UiState.Unauthorized }
                    )
                }
        }
    }

    fun imageUpload(file: List<MultipartBody.Part>) {
        viewModelScope.launch {
            imageUploadUseCase(file)
                .onSuccess { }
                .onFailure { }
        }
    }

    fun modifyRecipe(
        index:Long,
        body: RecipesRequestModel
    ) {
        viewModelScope.launch {
            modifyRecipeUseCase(index, body)
                .onSuccess {
                    _modifyUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _modifyUiState.value = UiState.BadRequest },
                        unauthorizedAction = { _modifyUiState.value = UiState.Unauthorized },
                        forbiddenAction = { _modifyUiState.value = UiState.Forbidden },
                        notFoundAction = { _modifyUiState.value = UiState.NotFound }
                    )
                }
        }
    }

    fun getRecipeDetail(index: Long) {
        viewModelScope.launch {
            getRecipeDetailUseCase(index)
                .onSuccess {
                    _recipeUiState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = { _recipeUiState.value = UiState.Unauthorized },
                        notFoundAction = { _recipeUiState.value = UiState.NotFound }
                    )
                }
        }
    }
}