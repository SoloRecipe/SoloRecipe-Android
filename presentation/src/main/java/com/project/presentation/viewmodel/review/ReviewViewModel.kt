package com.project.presentation.viewmodel.review

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.review.request.ReviewRequestModel
import com.project.domain.usecase.review.DeleteReviewUseCase
import com.project.domain.usecase.review.ModifyReviewUseCase
import com.project.domain.usecase.review.WriteReviewUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewViewModel @Inject constructor(
    private val deleteReviewUseCase: DeleteReviewUseCase,
    private val modifyReviewUseCase: ModifyReviewUseCase,
    private val writeReviewUseCase: WriteReviewUseCase
): ViewModel() {
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