package com.project.data.remote.datasource.recipe

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.data.remote.model.response.RecipeResponse
import com.project.data.remote.network.api.RecipeApi
import com.project.data.remote.util.safeApiCall

class RecipePagingSource(
    private val recipeApi: RecipeApi,
    private val filter: String? = null
) : PagingSource<Int, RecipeResponse>() {
    override fun getRefreshKey(state: PagingState<Int, RecipeResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeResponse> {
        val page = params.key ?: 1
        val response = safeApiCall {
            if (filter == "recommend") {
                recipeApi.getAllRecipes(
                    page = page,
                    size = params.loadSize
                )
            } else {
                recipeApi.getAllRecipes(
                    page = page,
                    size = params.loadSize
                )
            }
        }

        return LoadResult.Page(
            data = response.recipeList,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (response.recipeList.isEmpty()) null else page + 1
        )
    }
}