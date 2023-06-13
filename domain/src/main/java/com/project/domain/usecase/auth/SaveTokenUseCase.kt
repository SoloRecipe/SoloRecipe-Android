package com.project.domain.usecase.auth

import com.project.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(accessToken: String, refreshToken: String, accessTokenExp: String, refreshTokenExp: String) =
        kotlin.runCatching { repository.saveToken(accessToken, refreshToken, accessTokenExp, refreshTokenExp) }
}