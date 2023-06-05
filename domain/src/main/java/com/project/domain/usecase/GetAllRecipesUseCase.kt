package com.project.domain.usecase

import com.project.domain.repository.RecipeRepository
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke() = repository.getAllRecipes()
}