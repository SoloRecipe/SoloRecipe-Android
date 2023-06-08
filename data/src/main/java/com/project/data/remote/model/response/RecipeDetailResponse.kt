package com.project.data.remote.model.response

import com.project.domain.model.recipe.response.RecipeDetailResponseModel
import com.project.domain.model.recipe.response.RecipeProcessModel
import com.project.domain.model.recipe.response.ReviewModel

data class RecipeDetailResponse(
    val idx: Long,
    val name: String,
    val thumbnail: String,
    val recipeProcess: List<RecipeProcess>,
    val reviews: List<Review>
)

data class RecipeProcess(
    val idx: Long,
    val description: String,
    val image: String
)

data class Review(
    val userIdx: Long,
    val userName: String,
    val content: String
)

fun RecipeDetailResponse.asRecipeDetailResponseModel() = RecipeDetailResponseModel(
    idx = idx,
    name = name,
    thumbnail = thumbnail,
    recipeProcess = recipeProcess.map { it.asRecipeProcessModel() },
    reviews = reviews.map { it.asReviewModel() }
)

fun RecipeProcess.asRecipeProcessModel() = RecipeProcessModel(
    idx = idx,
    description = description,
    image = image
)

fun Review.asReviewModel() = ReviewModel(
    userIdx = userIdx,
    userName = userName,
    content = content
)