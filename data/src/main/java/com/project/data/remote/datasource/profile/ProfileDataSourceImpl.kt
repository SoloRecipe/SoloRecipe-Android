package com.project.data.remote.datasource.profile

import com.project.data.remote.model.request.ProfileRequest
import com.project.data.remote.model.response.ProfilesResponse
import com.project.data.remote.network.ProfileApi
import com.project.data.remote.util.safeApiCall
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val profileApi: ProfileApi
): ProfileDataSource {
    override suspend fun getUserInfo(): ProfilesResponse = safeApiCall { profileApi.getUserInfo() }

    override suspend fun renameUserName(body: ProfileRequest) = safeApiCall { profileApi.renameUserName(body) }

    override suspend fun deleteUserInfo() = safeApiCall { profileApi.deleteUserInfo() }
}