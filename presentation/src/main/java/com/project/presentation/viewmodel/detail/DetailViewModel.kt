package com.project.presentation.viewmodel.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.recipe.response.RecipeDetailResponseModel
import com.project.domain.model.review.request.ReviewRequestModel
import com.project.domain.usecase.like.LikeRecipeUseCase
import com.project.domain.usecase.recipe.GetRecipeDetailUseCase
import com.project.domain.usecase.review.WriteReviewUseCase
import com.project.presentation.viewmodel.util.UiState
import com.project.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase,
    private val likeRecipeUseCase: LikeRecipeUseCase,
    private val writeReviewUseCase: WriteReviewUseCase
) : ViewModel() {
    private val _recipeUiState: MutableStateFlow<UiState<RecipeDetailResponseModel>> = MutableStateFlow(UiState.Loading)
    val recipeUiState = _recipeUiState.asStateFlow()

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

    fun likeRecipe(index: Long) {
        viewModelScope.launch {
            likeRecipeUseCase(index)
                .onSuccess {
                    Log.d("likeRecipe", "success")
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { },
                        unauthorizedAction = { },
                        notFoundAction = { }
                    )
                }
        }
    }

    fun writeReview(recipeIndex: Long, body: ReviewRequestModel) {
        viewModelScope.launch {
            writeReviewUseCase(recipeIndex, body)
                .onSuccess {
                    getRecipeDetail(recipeIndex)
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { },
                        unauthorizedAction = { },
                        notFoundAction = { }
                    )
                }
        }
    }
}