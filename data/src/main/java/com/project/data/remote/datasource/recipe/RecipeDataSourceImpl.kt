package com.project.data.remote.datasource.recipe

import com.project.data.remote.model.request.RecipesRequest
import com.project.data.remote.model.response.RecipeResponse
import com.project.data.remote.network.api.RecipeApi
import com.project.data.remote.util.safeApiCall
import javax.inject.Inject

class RecipeDataSourceImpl @Inject constructor(
    private val recipeApi: RecipeApi
): RecipeDataSource {
    override suspend fun searchRecipe(name: String): List<RecipeResponse> = safeApiCall {
        recipeApi.searchRecipe(name)
    }

    override suspend fun createRecipe(body: RecipesRequest) = safeApiCall {
        recipeApi.createRecipe(body)
    }

    override suspend fun getRecipeDetail(index: Long) = safeApiCall {
        recipeApi.getRecipeDetail(index)
    }

    override suspend fun modifyRecipe(index: Long, body: RecipesRequest) = safeApiCall {
        recipeApi.modifyRecipe(index, body)
    }

    override suspend fun deleteRecipe(index: Long) = safeApiCall {
        recipeApi.deleteRecipe(index)
    }
}