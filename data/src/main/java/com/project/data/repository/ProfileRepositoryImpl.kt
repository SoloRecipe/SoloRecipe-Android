package com.project.data.repository

import com.project.data.local.datasource.LocalDataSource
import com.project.data.remote.datasource.profile.ProfileDataSource
import com.project.data.remote.model.request.asProfileImageRequest
import com.project.data.remote.model.request.asProfileRequest
import com.project.data.remote.model.response.asProfilesResponseModel
import com.project.domain.model.profile.request.ProfileImageRequestModel
import com.project.domain.model.profile.request.ProfileRequestModel
import com.project.domain.model.profile.response.ProfilesResponseModel
import com.project.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource,
    private val localDataSource: LocalDataSource
): ProfileRepository {
    override suspend fun getUserInfo(): ProfilesResponseModel =
        profileDataSource.getUserInfo().asProfilesResponseModel()

    override suspend fun renameUserName(profileRequestModel: ProfileRequestModel) =
        profileDataSource.renameUserName(profileRequestModel.asProfileRequest())

    override suspend fun deleteUserInfo(header: String) =
        profileDataSource.deleteUserInfo(header)

    override suspend fun modifyProfileImage(profileImageRequestModel: ProfileImageRequestModel) =
        profileDataSource.modifyProfileImage(profileImageRequestModel.asProfileImageRequest())

    override suspend fun getRefreshToken(): Flow<String> =
        localDataSource.getRefreshToken()
}