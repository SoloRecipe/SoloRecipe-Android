package com.project.data.remote.model.request

import com.project.domain.model.recipe.request.RecipeRequestModel

data class RecipeRequest(
    val description: String,
    val image: String
)

fun RecipeRequestModel.asRecipeRequest() = RecipeRequest(
    description = description,
    image = image
)
