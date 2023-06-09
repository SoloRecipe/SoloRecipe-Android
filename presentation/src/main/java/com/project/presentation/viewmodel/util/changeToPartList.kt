package com.project.presentation.viewmodel.util

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun changeToPartList(file: File): List<MultipartBody.Part> {
    val requestFile: RequestBody =
        RequestBody.create(MediaType.parse("multipart/form-data"), file)
    val part: MultipartBody.Part =
        MultipartBody.Part.createFormData("file", file.name, requestFile)
    val partList: List<MultipartBody.Part> = mutableListOf(part)

    return partList
}