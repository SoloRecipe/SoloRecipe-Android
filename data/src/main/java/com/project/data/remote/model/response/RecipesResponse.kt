package com.project.data.remote.model.response

import com.project.domain.model.auth.response.RecipesResponseModel

data class RecipesResponse(
    val page: Int,
    val size: Int,
    val recipeList: List<RecipeResponse>
)

fun RecipesResponse.asRecipesResponseModel() = RecipesResponseModel(
    page = page,
    size = size,
    recipeList = recipeList.map { it.asRecipeResponseModel() }
)