package com.project.domain.repository

import com.project.domain.model.review.request.ReviewRequestModel

interface ReviewRepository {
    suspend fun writeReview(recipeIndex: Long)

    suspend fun modifyReview(
        recipeIndex: Long,
        body: ReviewRequestModel
    )

    suspend fun deleteReview(recipeIndex: Long)
}