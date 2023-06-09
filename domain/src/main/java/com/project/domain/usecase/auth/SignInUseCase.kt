package com.project.domain.usecase.auth

import com.project.domain.model.auth.request.SignInRequestModel
import com.project.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(signInRequestModel: SignInRequestModel) = kotlin.runCatching { repository.signIn(signInRequestModel) }
}