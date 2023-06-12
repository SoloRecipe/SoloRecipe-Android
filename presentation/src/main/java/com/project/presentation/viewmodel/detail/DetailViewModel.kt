package com.project.presentation.viewmodel.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.recipe.response.RecipeDetailResponseModel
import com.project.domain.model.review.request.ReviewRequestModel
import com.project.domain.usecase.like.LikeRecipeUseCase
import com.project.domain.usecase.like.UnlikeRecipeUseCase
import com.project.domain.usecase.recipe.DeleteRecipeUseCase
import com.project.domain.usecase.recipe.GetRecipeDetailUseCase
import com.project.domain.usecase.review.DeleteReviewUseCase
import com.project.domain.usecase.review.ModifyReviewUseCase
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
    private val deleteReviewUseCase: DeleteReviewUseCase,
    private val modifyReviewUseCase: ModifyReviewUseCase,
    private val writeReviewUseCase: WriteReviewUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val unlikeRecipeUseCase: UnlikeRecipeUseCase
) : ViewModel() {
    private val _recipeUiState: MutableStateFlow<UiState<RecipeDetailResponseModel>> = MutableStateFlow(UiState.Loading)
    val recipeUiState = _recipeUiState.asStateFlow()

    private val _deleteUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val deleteUiState = _recipeUiState.asStateFlow()

    private val _modifyUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val modifyUiState = _modifyUiState.asStateFlow()

    private val _deleteRecipeUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val deleteRecipeUiState = _deleteRecipeUiState.asStateFlow()

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

    fun deleteReview(recipeIndex: Long) {
        viewModelScope.launch {
            deleteReviewUseCase(recipeIndex)
                .onSuccess {
                    _deleteUiState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _deleteUiState.value = UiState.BadRequest },
                        notFoundAction = { _deleteUiState.value = UiState.NotFound },
                        forbiddenAction = { _deleteUiState.value = UiState.Forbidden }
                    )
                }
        }
    }

    fun modifyReview(recipeIndex: Long, body: ReviewRequestModel) {
        viewModelScope.launch {
            modifyReviewUseCase(recipeIndex, body)
                .onSuccess {
                    _modifyUiState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _modifyUiState.value = UiState.BadRequest },
                        notFoundAction = { _modifyUiState.value = UiState.NotFound },
                        forbiddenAction = { _modifyUiState.value = UiState.Forbidden }
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

    fun deleteRecipe(index: Long) {
        viewModelScope.launch {
            deleteRecipeUseCase(index)
                .onSuccess {
                    _deleteRecipeUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = { _deleteRecipeUiState.value = UiState.Unauthorized },
                        forbiddenAction = { _deleteRecipeUiState.value = UiState.Forbidden },
                        notFoundAction = { _deleteRecipeUiState.value = UiState.NotFound }
                    )
                }
        }
    }

    fun unlikeRecipe(index: Long) {
        viewModelScope.launch {
            unlikeRecipeUseCase(index)
                .onSuccess {
                    Log.d("unlike", "success")
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = {},
                        unauthorizedAction = {},
                        notFoundAction = {}
                    )
                }
        }
    }
}