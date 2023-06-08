package com.project.domain.model.recipe.response


data class RecipeDetailResponseModel(
    val idx: Long,
    val name: String,
    val thumbnail: String,
    val recipeProcess: List<RecipeProcessModel>,
    val reviews: List<ReviewModel>
)

data class RecipeProcessModel(
    val idx: Long,
    val description: String,
    val image: String
)

data class ReviewModel(
    val userIdx: Long,
    val userName: String,
    val content: String
)