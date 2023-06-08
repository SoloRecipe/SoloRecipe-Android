package com.project.domain.repository

import com.project.domain.model.profile.response.ImageUploadResponseModel
import okhttp3.MultipartBody


interface ImageRepository {
    suspend fun imageUpload(file: List<MultipartBody.Part>): ImageUploadResponseModel
}