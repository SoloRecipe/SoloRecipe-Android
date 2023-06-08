package com.project.domain.usecase.recipe

import com.project.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecommendRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke() = repository.getRecommendRecipes()
}