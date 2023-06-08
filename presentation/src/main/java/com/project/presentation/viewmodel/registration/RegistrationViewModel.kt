package com.project.presentation.viewmodel.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.domain.usecase.recipe.CreateRecipeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
  private val createRecipeUseCase: CreateRecipeUseCase
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
}