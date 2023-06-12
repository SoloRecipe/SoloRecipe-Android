package com.project.data.remote.model.response

import com.project.domain.model.profile.response.ImageUploadResponseModel

data class ImageUploadResponse(
    val images: List<String>
)

fun ImageUploadResponse.asImageUploadResponseModel() = ImageUploadResponseModel(
    images = images
)