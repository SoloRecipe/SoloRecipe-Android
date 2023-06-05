package com.project.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.project.data.remote.datasource.recipe.RecipeDataSource
import com.project.data.remote.datasource.recipe.RecipePagingSource
import com.project.data.remote.model.response.asRecipeResponseModel
import com.project.data.remote.network.RecipeApi
import com.project.domain.model.auth.response.RecipeResponseModel
import com.project.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val recipeDataSource: RecipeDataSource
) : RecipeRepository {
    override fun getRecommendRecipes(): Flow<PagingData<RecipeResponseModel>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = {
                RecipePagingSource(
                    recipeApi = recipeApi,
                    filter = "recommend"
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { it.asRecipeResponseModel() }
        }
    }

    override fun getAllRecipes(): Flow<PagingData<RecipeResponseModel>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = {
                RecipePagingSource(recipeApi = recipeApi)
            }
        ).flow.map { pagingData ->
            pagingData.map { it.asRecipeResponseModel() }
        }
    }

    override suspend fun searchRecipe(name: String): RecipeResponseModel =
        recipeDataSource.searchRecipe(name = name).asRecipeResponseModel()
}