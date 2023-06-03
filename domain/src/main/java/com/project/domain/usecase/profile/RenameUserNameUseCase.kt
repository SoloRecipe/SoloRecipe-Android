package com.project.domain.usecase.profile

import com.project.domain.model.profile.request.ProfileRequestModel
import com.project.domain.repository.ProfileRepository
import javax.inject.Inject

class RenameUserNameUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(profileRequestModel: ProfileRequestModel) = kotlin.runCatching { repository.renameUserName(profileRequestModel) }
}