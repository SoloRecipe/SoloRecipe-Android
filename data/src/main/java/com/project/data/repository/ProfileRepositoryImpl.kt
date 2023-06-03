package com.project.data.repository

import com.project.data.remote.datasource.profile.ProfileDataSource
import com.project.data.remote.model.request.asProfileRequest
import com.project.data.remote.model.response.asProfilesResponseModel
import com.project.domain.model.profile.request.ProfileRequestModel
import com.project.domain.model.profile.response.ProfilesResponseModel
import com.project.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
): ProfileRepository {
    override suspend fun getUserInfo(): ProfilesResponseModel =
        profileDataSource.getUserInfo().asProfilesResponseModel()

    override suspend fun renameUserName(profileRequestModel: ProfileRequestModel) =
        profileDataSource.renameUserName(profileRequestModel.asProfileRequest())

    override suspend fun deleteUserInfo() =
        profileDataSource.deleteUserInfo()
}