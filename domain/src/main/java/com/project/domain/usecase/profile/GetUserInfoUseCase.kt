package com.project.domain.usecase.profile

import com.project.domain.repository.ProfileRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
  private val repository: ProfileRepository
) {
    suspend operator fun invoke() = kotlin.runCatching { repository.getUserInfo() }
}