package com.project.domain.repository

import com.project.domain.model.profile.request.ProfileImageRequestModel
import com.project.domain.model.profile.request.ProfileRequestModel
import com.project.domain.model.profile.response.ProfilesResponseModel

interface ProfileRepository {
    suspend fun getUserInfo(): ProfilesResponseModel

    suspend fun renameUserName(profileRequestModel: ProfileRequestModel)

    suspend fun deleteUserInfo(header: String)

    suspend fun modifyProfileImage(profileImageRequestModel: ProfileImageRequestModel)
}