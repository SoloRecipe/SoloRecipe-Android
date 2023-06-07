package com.project.data.remote.model.request

data class RecipesRequest(
    val name: String,
    val thumbnail: String,
    val recipeProcess: List<RecipeRequest>
)