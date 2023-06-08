package com.project.data.remote.datasource.review

import com.project.data.remote.model.request.ReviewRequest

interface ReviewDataSource {
    suspend fun writeReview(
        recipeIndex: Long,
        body: ReviewRequest
    )

    suspend fun modifyReview(
        recipeIndex: Long,
        body: ReviewRequest
    )

    suspend fun deleteReview(recipeIndex: Long)
}