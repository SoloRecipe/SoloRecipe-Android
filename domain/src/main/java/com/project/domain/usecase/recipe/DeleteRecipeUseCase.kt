package com.project.domain.usecase.recipe

import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.domain.repository.RecipeRepository
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(index: Long) = kotlin.runCatching { repository.deleteRecipe(index) }
}