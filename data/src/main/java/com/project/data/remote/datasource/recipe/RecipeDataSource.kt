package com.project.data.remote.datasource.recipe

import com.project.data.remote.model.request.RecipesRequest
import com.project.data.remote.model.response.RecipeDetailResponse
import com.project.data.remote.model.response.RecipeResponse

interface RecipeDataSource {
    suspend fun searchRecipe(name: String): List<RecipeResponse>

    suspend fun createRecipe(body: RecipesRequest)

    suspend fun getRecipeDetail(index: Long): RecipeDetailResponse

    suspend fun modifyRecipe(
        index: Long,
        body: RecipesRequest
    )

    suspend fun deleteRecipe(index: Long)
}