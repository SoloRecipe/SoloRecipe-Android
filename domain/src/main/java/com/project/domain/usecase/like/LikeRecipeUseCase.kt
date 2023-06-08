package com.project.domain.usecase.like

import com.project.domain.repository.LikeRepository
import javax.inject.Inject

class LikeRecipeUseCase @Inject constructor(
    private val repository: LikeRepository
) {
    suspend operator fun invoke() = kotlin.runCatching { repository.likeRecipe() }
}