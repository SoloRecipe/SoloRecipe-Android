package com.project.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.project.data.remote.datasource.recipe.AllRecipePagingSource
import com.project.data.remote.datasource.recipe.RecipeDataSource
import com.project.data.remote.datasource.recipe.RecommendRecipePagingSource
import com.project.data.remote.model.request.asRecipesRequest
import com.project.data.remote.model.response.asRecipeDetailResponseModel
import com.project.data.remote.model.response.asRecipeResponseModel
import com.project.data.remote.network.api.RecipeApi
import com.project.domain.model.auth.response.RecipeResponseModel
import com.project.domain.model.recipe.request.RecipesRequestModel
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
            config = PagingConfig(pageSize = 2),
            pagingSourceFactory = {
                RecommendRecipePagingSource(
                    recipeApi = recipeApi
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { it.asRecipeResponseModel() }
        }
    }

    override fun getAllRecipes(): Flow<PagingData<RecipeResponseModel>> {
        return Pager(
            config = PagingConfig(pageSize = 2),
            pagingSourceFactory = {
                AllRecipePagingSource(recipeApi = recipeApi)
            }
        ).flow.map { pagingData ->
            pagingData.map { it.asRecipeResponseModel() }
        }
    }

    override suspend fun searchRecipe(name: String): RecipeResponseModel =
        recipeDataSource.searchRecipe(name = name).asRecipeResponseModel()

    override suspend fun createRecipe(body: RecipesRequestModel) =
        recipeDataSource.createRecipe(body.asRecipesRequest())

    override suspend fun getRecipeDetail(index: Long) = recipeDataSource.getRecipeDetail(index).asRecipeDetailResponseModel()
    override suspend fun modifyRecipe(index: Long, body: RecipesRequestModel) =
        recipeDataSource.modifyRecipe(index, body.asRecipesRequest())

    override suspend fun deleteRecipe(index: Long) =
        recipeDataSource.deleteRecipe(index)
}