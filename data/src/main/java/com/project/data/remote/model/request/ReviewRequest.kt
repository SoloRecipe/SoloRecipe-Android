package com.project.data.remote.model.request

import com.project.domain.model.review.request.ReviewRequestModel

data class ReviewRequest(
    val content: String
)

fun ReviewRequestModel.asReviewRequest() = ReviewRequest(
    content = content
)