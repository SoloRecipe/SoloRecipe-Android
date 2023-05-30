package com.project.data.remote.model.request

data class SignInRequest(
    val email: String,
    val password: String,
    val nickname: String
)