package com.project.presentation.viewmodel.image

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.image.ImageUploadUseCase
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

class ImageUploadViewModel @Inject constructor(
    private val imageUploadUseCase: ImageUploadUseCase
): ViewModel() {
    fun imageUpload(file: List<MultipartBody.Part>) {
        viewModelScope.launch {
            imageUploadUseCase(file)
                .onSuccess {
                    Log.d("imageUpload", it.toString())
                }
                .onFailure {
                    Log.d("imageUpload", it.message.toString())
                }
        }
    }
}