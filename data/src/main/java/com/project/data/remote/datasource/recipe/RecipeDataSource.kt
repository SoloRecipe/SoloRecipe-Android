package com.project.data.remote.datasource.recipe

import com.project.data.remote.model.request.RecipesRequest
import com.project.data.remote.model.response.RecipeResponse

interface RecipeDataSource {
    suspend fun searchRecipe(name: String): RecipeResponse

    suspend fun createRecipe(body: RecipesRequest)

    suspend fun getRecipeDetail(index: Long)
}