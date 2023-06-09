package com.project.domain.usecase.profile

import com.project.domain.repository.ProfileRepository
import javax.inject.Inject

class DeleteUserInfoUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(header: String) = kotlin.runCatching { repository.deleteUserInfo(header) }
}