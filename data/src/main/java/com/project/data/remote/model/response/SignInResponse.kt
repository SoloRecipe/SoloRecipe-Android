package com.project.data.remote.model.response

import com.project.domain.model.auth.response.SignInResponseModel

data class SignInResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: String,
    val refreshExp: String
)

fun SignInResponse.asSignInResponseModel() = SignInResponseModel(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessExp = accessExp,
    refreshExp = refreshExp
)