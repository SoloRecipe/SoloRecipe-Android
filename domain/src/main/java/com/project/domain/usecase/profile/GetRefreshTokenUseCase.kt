package com.project.domain.usecase.profile

import com.project.domain.repository.ProfileRepository
import javax.inject.Inject

class GetRefreshTokenUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke() = kotlin.runCatching { profileRepository.getRefreshToken() }
}