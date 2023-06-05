package com.project.data.remote.model.response

import com.project.domain.model.auth.response.RecipeResponseModel

data class RecipeResponse(
    val idx: Long,
    val name: String,
    val thumbnail: String
)

fun RecipeResponse.asRecipeResponseModel() = RecipeResponseModel(
    idx = idx,
    name = name,
    thumbnail = thumbnail
)