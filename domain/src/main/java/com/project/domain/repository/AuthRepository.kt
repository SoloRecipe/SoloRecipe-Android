package com.project.domain.repository

import com.project.domain.model.auth.request.SignInRequestModel
import com.project.domain.model.auth.request.SignUpRequestModel
import com.project.domain.model.auth.response.SignInResponseModel

interface AuthRepository {
    suspend fun signIn(signInRequestModel: SignInRequestModel): SignInResponseModel

    suspend fun signUp(signUpRequestModel: SignUpRequestModel)

    suspend fun isLogin(): Boolean

    suspend fun saveToken(
        accessToken: String,
        refreshToken: String,
        accessTokenExp: String,
        refreshTokenExp: String
    )
}