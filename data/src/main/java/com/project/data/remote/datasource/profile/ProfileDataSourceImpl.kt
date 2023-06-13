package com.project.data.remote.datasource.profile

import com.project.data.remote.model.request.ProfileImageRequest
import com.project.data.remote.model.request.ProfileRequest
import com.project.data.remote.model.response.ProfilesResponse
import com.project.data.remote.network.api.ProfileApi
import com.project.data.remote.util.safeApiCall
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val profileApi: ProfileApi
): ProfileDataSource {
    override suspend fun getUserInfo(): ProfilesResponse = safeApiCall { profileApi.getUserInfo() }

    override suspend fun renameUserName(body: ProfileRequest) = safeApiCall { profileApi.renameUserName(body) }

    override suspend fun deleteUserInfo(header: String) = safeApiCall { profileApi.deleteUserInfo(header) }

    override suspend fun modifyProfileImage(body: ProfileImageRequest) = safeApiCall { profileApi.modifyProfileImage(body) }
}