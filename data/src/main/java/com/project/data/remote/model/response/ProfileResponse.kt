package com.project.data.remote.model.response

import com.project.domain.model.profile.response.ProfileResponseModel

data class ProfileResponse(
    val idx: Long,
    val name: String,
    val thumbnail: String
)

fun ProfileResponse.asProfileResponseModel() = ProfileResponseModel(
    idx = idx,
    name = name,
    thumbnail = thumbnail
)