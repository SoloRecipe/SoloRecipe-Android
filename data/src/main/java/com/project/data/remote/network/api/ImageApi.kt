package com.project.data.remote.network.api

import com.project.data.remote.model.response.ImageUploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageApi {
    @Multipart
    @POST("image")
    suspend fun imageUpload(
        @Part file: List<MultipartBody.Part>
    ): ImageUploadResponse
}