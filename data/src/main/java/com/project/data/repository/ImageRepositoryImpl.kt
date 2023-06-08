package com.project.data.repository

import com.project.data.remote.datasource.image.ImageDataSource
import com.project.data.remote.model.response.asImageUploadResponseModel
import com.project.domain.model.profile.response.ImageUploadResponseModel
import com.project.domain.repository.ImageRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDataSource: ImageDataSource
): ImageRepository {
    override suspend fun imageUpload(file: List<MultipartBody.Part>): ImageUploadResponseModel =
        imageDataSource.imageUpload(file).asImageUploadResponseModel()
}