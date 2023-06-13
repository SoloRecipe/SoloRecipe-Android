package com.project.presentation.viewmodel.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.profile.response.ImageUploadResponseModel
import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.domain.usecase.image.ImageUploadUseCase
import com.project.domain.usecase.recipe.CreateRecipeUseCase
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
) : ViewModel() {
    private val _createUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val createUiState = _createUiState.asStateFlow()

    private val _modifyUiState: MutableStateFlow<UiState<ImageUploadResponseModel>> = MutableStateFlow(UiState.Loading)
    val modifyUiState = _modifyUiState.asStateFlow()
    val recipeImages = mutableListOf<String>("")
    val recipeTexts = mutableListOf<String>("")

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

    fun imageUpload(file: List<MultipartBody.Part>, index: Int) {
        viewModelScope.launch {
            imageUploadUseCase(file)
                .onSuccess {
                    recipeImages[index] = it.images[0]
                    _modifyUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _modifyUiState.value = UiState.BadRequest },
                        unauthorizedAction = { _modifyUiState.value = UiState.Unauthorized }
                    )
                }
        }
    }


}