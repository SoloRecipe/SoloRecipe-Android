package com.project.domain.usecase.review

import com.project.domain.repository.ReviewRepository
import javax.inject.Inject

class WriteReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(recipeIndex: Long) = kotlin.runCatching { repository.writeReview(recipeIndex) }
}