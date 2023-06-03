package com.project.data.remote.model.response

import com.project.domain.model.profile.response.ProfileResponseModel

data class ProfileResponse(
    val name: String,
    val thumbnail: String
)

fun ProfileResponse.asProfileResponseModel() = ProfileResponseModel(
    name = name,
    thumbnail = thumbnail
)