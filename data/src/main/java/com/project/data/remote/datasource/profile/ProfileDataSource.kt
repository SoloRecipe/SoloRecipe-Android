package com.project.data.remote.datasource.profile

import com.project.data.remote.model.request.ProfileImageRequest
import com.project.data.remote.model.request.ProfileRequest
import com.project.data.remote.model.response.ProfilesResponse

interface ProfileDataSource {
    suspend fun getUserInfo(): ProfilesResponse

    suspend fun renameUserName(body: ProfileRequest)

    suspend fun deleteUserInfo()

    suspend fun modifyProfileImage(body: ProfileImageRequest)
}