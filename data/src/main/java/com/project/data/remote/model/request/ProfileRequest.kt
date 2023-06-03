package com.project.data.remote.model.request

import com.project.domain.model.profile.request.ProfileRequestModel

data class ProfileRequest(
    val name: String
)

fun ProfileRequestModel.asProfileRequest() = ProfileRequest(
    name = name
)