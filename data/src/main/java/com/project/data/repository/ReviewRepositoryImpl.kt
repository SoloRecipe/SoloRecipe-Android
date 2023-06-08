package com.project.data.repository

import com.project.data.remote.datasource.review.ReviewDataSource
import com.project.data.remote.model.request.asReviewRequest
import com.project.domain.model.review.request.ReviewRequestModel
import com.project.domain.repository.ReviewRepository
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewDataSource: ReviewDataSource
): ReviewRepository {
    override suspend fun writeReview(recipeIndex: Long) = reviewDataSource.writeReview(recipeIndex)

    override suspend fun modifyReview(recipeIndex: Long, body: ReviewRequestModel) =
        reviewDataSource.modifyReview(recipeIndex, body.asReviewRequest())


    override suspend fun deleteReview(recipeIndex: Long) = reviewDataSource.deleteReview(recipeIndex)
}