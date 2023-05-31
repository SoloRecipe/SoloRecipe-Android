package com.project.data.remote.datasource.recipe

import com.project.data.remote.model.response.RecipeResponse
import com.project.data.remote.network.RecipeApi
import com.project.data.remote.util.safeApiCall
import javax.inject.Inject

class RecipeDataSourceImpl @Inject constructor(
    private val recipeApi: RecipeApi
): RecipeDataSource {
    override suspend fun searchRecipe(name: String): RecipeResponse = safeApiCall {
        recipeApi.searchRecipe(name)
    }
}