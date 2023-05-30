package com.project.data.remote.model.request

import com.project.domain.model.auth.request.SignUpRequestModel

data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String
)

fun SignUpRequestModel.asSignUpRequest() = SignUpRequest(
    email = email,
    password = password,
    nickname = nickname
)