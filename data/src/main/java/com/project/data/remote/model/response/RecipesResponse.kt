package com.project.data.remote.model.response

data class RecipesResponse(
    val page: Int,
    val size: Int,
    val recipeList: List<RecipeResponse>
)

