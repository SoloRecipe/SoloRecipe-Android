package com.project.domain.repository

import androidx.paging.PagingData
import com.project.domain.model.auth.response.RecipeResponseModel
import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.domain.model.recipe.response.RecipeDetailResponseModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecommendRecipes(): Flow<PagingData<RecipeResponseModel>>

    fun getAllRecipes(): Flow<PagingData<RecipeResponseModel>>

    suspend fun searchRecipe(name: String): RecipeResponseModel

    suspend fun createRecipe(body: RecipesRequestModel)

    suspend fun getRecipeDetail(index: Long): RecipeDetailResponseModel

    suspend fun modifyRecipe(
        index: Long,
        body: RecipesRequestModel
    )

    suspend fun deleteRecipe(index: Long)
}