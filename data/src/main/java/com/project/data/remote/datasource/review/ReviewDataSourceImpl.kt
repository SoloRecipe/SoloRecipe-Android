package com.project.data.remote.datasource.review

import com.project.data.remote.model.request.ReviewRequest
import com.project.data.remote.network.api.ReviewApi
import com.project.data.remote.util.safeApiCall
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor(
    private val reviewApi: ReviewApi
): ReviewDataSource {
    override suspend fun writeReview(recipeIndex: Long, body: ReviewRequest) = safeApiCall { reviewApi.writeReview(recipeIndex, body) }

    override suspend fun modifyReview(recipeIndex: Long, body: ReviewRequest) = safeApiCall { reviewApi.modifyReview(recipeIndex, body) }


    override suspend fun deleteReview(recipeIndex: Long) = safeApiCall { reviewApi.deleteReview(recipeIndex) }
}