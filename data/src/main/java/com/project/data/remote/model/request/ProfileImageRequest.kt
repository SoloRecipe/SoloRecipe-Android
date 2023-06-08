package com.project.data.remote.model.request

import com.project.domain.model.profile.request.ProfileImageRequestModel

data class ProfileImageRequest(
    val profileImg: String
)

fun ProfileImageRequestModel.asProfileImageRequest() = ProfileImageRequest(
    profileImg = profileImg
)