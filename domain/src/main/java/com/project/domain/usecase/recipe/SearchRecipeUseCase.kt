package com.project.domain.usecase.recipe

import com.project.domain.repository.RecipeRepository
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(name: String) = kotlin.runCatching { repository.searchRecipe(name) }
}