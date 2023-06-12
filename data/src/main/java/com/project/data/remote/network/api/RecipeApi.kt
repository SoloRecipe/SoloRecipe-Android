package com.project.data.remote.network.api

import com.project.data.remote.model.request.RecipesRequest
import com.project.data.remote.model.response.RecipeDetailResponse
import com.project.data.remote.model.response.RecipeResponse
import com.project.data.remote.model.response.RecipesResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
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
    ): List<RecipeResponse>

    @POST("recipe")
    suspend fun createRecipe(
        @Body body: RecipesRequest
    )

    @GET("recipe/detail/{idx}")
    suspend fun getRecipeDetail(
        @Path("idx") index: Long
    ): RecipeDetailResponse

    @PATCH("recipe/{idx}")
    suspend fun modifyRecipe(
        @Path("idx") index: Long,
        @Body body: RecipesRequest
    )

    @DELETE("recipe/{idx}")
    suspend fun deleteRecipe(
        @Path("idx") index: Long
    )
}
