package com.project.data.remote.datasource.recipe

import com.project.data.remote.model.response.RecipeResponse

interface RecipeDataSource {
    suspend fun searchRecipe(name: String): RecipeResponse
}