package com.project.domain.usecase

import com.project.domain.model.review.request.ReviewRequestModel
import com.project.domain.repository.ReviewRepository
import javax.inject.Inject

class ModifyReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(reviewIndex: Long, body: ReviewRequestModel) =
        kotlin.runCatching { repository.modifyReview(reviewIndex, body) }
}