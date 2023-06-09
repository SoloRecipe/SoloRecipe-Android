package com.project.presentation.viewmodel.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.domain.usecase.image.ImageUploadUseCase
import com.project.domain.usecase.recipe.CreateRecipeUseCase
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
    private val modifyRecipeUseCase: ModifyRecipeUseCase
) : ViewModel() {
    private val _createUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val createUiState = _createUiState.asStateFlow()

    private val _imageUploadUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val imageUploadUiState = _imageUploadUiState.asStateFlow()

    private val _modifyUistate: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val modifyUiState = _modifyUistate.asStateFlow()
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
                .onSuccess {
                    _imageUploadUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _imageUploadUiState.value = UiState.BadRequest },
                        unauthorizedAction = { _imageUploadUiState.value = UiState.Unauthorized }
                    )
                }
        }
    }

    fun modifyRecipe(
        index:Long,
        body: RecipesRequestModel
    ) {
        viewModelScope.launch {
            modifyRecipeUseCase(index, body)
                .onSuccess {
                    _modifyUistate.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _modifyUistate.value = UiState.BadRequest },
                        unauthorizedAction = { _modifyUistate.value = UiState.Unauthorized }
                    )
                }
        }
    }
}