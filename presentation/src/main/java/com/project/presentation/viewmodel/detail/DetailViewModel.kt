package com.project.presentation.viewmodel.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.review.request.ReviewRequestModel
import com.project.domain.usecase.like.LikeRecipeUseCase
import com.project.domain.usecase.recipe.GetRecipeDetailUseCase
import com.project.domain.usecase.review.DeleteReviewUseCase
import com.project.domain.usecase.review.ModifyReviewUseCase
import com.project.domain.usecase.review.WriteReviewUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase,
    private val likeRecipeUseCase: LikeRecipeUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase,
    private val modifyReviewUseCase: ModifyReviewUseCase,
    private val writeReviewUseCase: WriteReviewUseCase
): ViewModel(){
    fun getRecipeDetail(index: Long) {
        viewModelScope.launch {
            getRecipeDetailUseCase(index)
                .onSuccess {
                    Log.d("recipeDetail", it.toString())
                }.onFailure {
                    Log.d("recipeDetail", it.message.toString())
                }
        }
    }

    fun likeRecipe() {
        viewModelScope.launch {
            likeRecipeUseCase()
                .onSuccess {
                    Log.d("likeRecipe", it.toString())
                }.onFailure {
                    Log.d("likeRecipe", it.message.toString())
                }
        }
    }

    fun deleteReview(recipeIndex: Long) {
        viewModelScope.launch {
            deleteReviewUseCase(recipeIndex)
                .onSuccess {
                    Log.d("deleteReview", it.toString())
                }.onFailure {
                    Log.d("deleteReview", it.message.toString())
                }
        }
    }

    fun modifyReview(recipeIndex: Long, body: ReviewRequestModel) {
        viewModelScope.launch {
            modifyReviewUseCase(recipeIndex, body)
                .onSuccess {
                    Log.d("modifyReview", it.toString())
                }.onFailure {
                    Log.d("modifyReview", it.message.toString())
                }
        }
    }

    fun writeReview(recipeIndex: Long) {
        viewModelScope.launch {
            writeReviewUseCase(recipeIndex)
                .onSuccess {
                    Log.d("writeReview", it.toString())
                }.onFailure {
                    Log.d("writeReview", it.message.toString())
                }
        }
    }
}