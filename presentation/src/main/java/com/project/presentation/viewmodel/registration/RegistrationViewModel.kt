package com.project.presentation.viewmodel.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.domain.usecase.image.ImageUploadUseCase
import com.project.domain.usecase.recipe.CreateRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
  private val createRecipeUseCase: CreateRecipeUseCase,
  private val imageUploadUseCase: ImageUploadUseCase
): ViewModel() {
    fun createRecipe(body: RecipesRequestModel) {
        viewModelScope.launch {
            createRecipeUseCase(body)
                .onSuccess {
                    Log.d("createRecipe", it.toString())
                }
                .onFailure {
                    Log.d("createRecipe", it.message.toString())
                }
        }
    }

    fun imageUpload(file: List<MultipartBody.Part>) {
        viewModelScope.launch {
            imageUploadUseCase(file)
                .onSuccess {
                    Log.d("imageUpload", it.toString())
                }
                .onFailure {
                    Log.d("imageUpload", it.message.toString())
                }
        }
    }
}