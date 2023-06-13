package com.project.domain.usecase.auth

import com.project.domain.repository.AuthRepository
import javax.inject.Inject

class IsLoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.isLogin()
}