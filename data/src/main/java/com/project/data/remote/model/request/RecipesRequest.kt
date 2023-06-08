package com.project.data.remote.model.request

import com.project.domain.model.recipe.request.RecipesRequestModel

data class RecipesRequest(
    val name: String,
    val thumbnail: String,
    val recipeProcess: List<RecipeRequest>
)

fun RecipesRequestModel.asRecipesRequest() = RecipesRequest(
    name = name,
    thumbnail = thumbnail,
    recipeProcess = recipeProcess.map { it.asRecipeRequest() }
)