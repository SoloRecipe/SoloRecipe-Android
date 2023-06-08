package com.project.domain.usecase.image

import com.project.domain.repository.ImageRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class ImageUploadUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(file: List<MultipartBody.Part>) = kotlin.runCatching { repository.imageUpload(file) }
}