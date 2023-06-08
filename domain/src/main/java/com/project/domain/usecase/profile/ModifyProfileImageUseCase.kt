package com.project.domain.usecase.profile

import com.project.domain.model.profile.request.ProfileImageRequestModel
import com.project.domain.repository.ProfileRepository
import javax.inject.Inject

class ModifyProfileImageUseCase @Inject constructor(
    val repository: ProfileRepository
) {
    suspend operator fun invoke(profileImageRequestModel: ProfileImageRequestModel) = kotlin.runCatching { repository.modifyProfileImage(profileImageRequestModel) }
}