package com.project.data.remote.network

import com.project.data.remote.model.response.RecipeResponse
import com.project.data.remote.model.response.RecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("recipe/suggest")
    suspend fun getRecommendRecipes(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): RecipesResponse

    @GET("recipe/all")
    suspend fun getAllRecipes(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): RecipesResponse

    @GET("recipe/search")
    suspend fun searchRecipe(
        @Query("name") name: String
    ): RecipeResponse
}