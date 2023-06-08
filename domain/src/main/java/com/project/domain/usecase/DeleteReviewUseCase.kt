package com.project.domain.usecase

import com.project.domain.repository.ReviewRepository
import javax.inject.Inject

class DeleteReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(recipeIndex: Long) = kotlin.runCatching { repository.deleteReview(recipeIndex) }
}