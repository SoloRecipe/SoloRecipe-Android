package com.project.data.remote.datasource.image

import com.project.data.remote.model.response.ImageUploadResponse
import okhttp3.MultipartBody

interface ImageDataSource {
    suspend fun imageUpload(file: List<MultipartBody.Part>): ImageUploadResponse
}