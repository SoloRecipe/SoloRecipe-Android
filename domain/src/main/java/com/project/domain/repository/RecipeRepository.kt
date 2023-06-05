package com.project.domain.repository

import androidx.paging.PagingData
import com.project.domain.model.auth.response.RecipeResponseModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecommendRecipes(): Flow<PagingData<RecipeResponseModel>>

    fun getAllRecipes(): Flow<PagingData<RecipeResponseModel>>

    suspend fun searchRecipe(name: String): RecipeResponseModel
}