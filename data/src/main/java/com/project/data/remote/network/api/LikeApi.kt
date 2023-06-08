package com.project.data.remote.network.api

import retrofit2.http.POST

interface LikeApi {
    @POST("like")
    suspend fun likeRecipe()
}