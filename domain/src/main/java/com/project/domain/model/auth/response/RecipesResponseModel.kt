package com.project.domain.model.auth.response

data class RecipesResponseModel(
    val page: Int,
    val size: Int,
    val recipeList: List<RecipeResponseModel>
)