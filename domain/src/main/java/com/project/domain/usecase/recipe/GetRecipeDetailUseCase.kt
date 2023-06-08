package com.project.domain.usecase.recipe

import com.project.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(index: Long) = repository.getRecipeDetail(index)
}