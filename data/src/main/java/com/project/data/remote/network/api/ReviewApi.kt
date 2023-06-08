package com.project.data.remote.network.api

import com.project.data.remote.model.request.ReviewRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewApi {
    @POST("review")
    suspend fun writeReview(
        @Path("recipe-idx") recipeIndex: Long,
        @Body body: ReviewRequest
    )

    @PATCH("review")
    suspend fun modifyReview(
        @Path("recipe-idx") recipeIndex: Long,
        @Body body: ReviewRequest
    )

    @DELETE("review")
    suspend fun deleteReview(
        @Path("recipe-idx") recipeIndex: Long
    )
}