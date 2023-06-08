package com.project.domain.model.recipe.request

data class RecipesRequestModel(
    val name: String,
    val thumbnail: String,
    val recipeProcess: List<RecipeRequestModel>
)
