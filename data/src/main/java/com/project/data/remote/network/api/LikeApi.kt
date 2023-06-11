package com.project.data.remote.network.api

import retrofit2.http.POST
import retrofit2.http.Path

interface LikeApi {
    @POST("like/{recipe-idx}")
    suspend fun likeRecipe(
        @Path("recipe-idx") index: Long
    )
}