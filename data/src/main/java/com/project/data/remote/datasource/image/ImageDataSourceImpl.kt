package com.project.data.remote.datasource.image

import com.project.data.remote.model.response.ImageUploadResponse
import com.project.data.remote.network.api.ImageApi
import com.project.data.remote.util.safeApiCall
import okhttp3.MultipartBody
import javax.inject.Inject

class ImageDataSourceImpl @Inject constructor(
    private val imageApi: ImageApi
): ImageDataSource {
    override suspend fun imageUpload(file: List<MultipartBody.Part>): ImageUploadResponse = safeApiCall { imageApi.imageUpload(file) }
}