package com.project.domain.model.auth.response

data class SignInResponseModel(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: String,
    val refreshExp: String
)