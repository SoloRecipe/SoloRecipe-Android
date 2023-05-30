package com.project.domain.usecase

import com.project.domain.model.auth.request.SignUpRequestModel
import com.project.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(signUpRequestModel: SignUpRequestModel) =
        kotlin.runCatching { repository.signUp(signUpRequestModel) }
}