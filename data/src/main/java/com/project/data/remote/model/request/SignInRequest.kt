package com.project.data.remote.model.request

import com.project.domain.model.auth.request.SignInRequestModel

data class SignInRequest(
    val email: String,
    val password: String,
)

fun SignInRequestModel.asSignInRequest() = SignInRequest(
    email = email,
    password = password
)