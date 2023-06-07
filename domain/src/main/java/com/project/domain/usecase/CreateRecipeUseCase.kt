package com.project.domain.usecase

import com.project.domain.model.recipe.RecipesRequestModel
import com.project.domain.repository.RecipeRepository
import javax.inject.Inject

class CreateRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(body: RecipesRequestModel) = kotlin.runCatching { repository.createRecipe(body) }
}