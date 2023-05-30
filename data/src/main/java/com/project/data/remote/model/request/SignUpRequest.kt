package com.project.data.remote.model.request

data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String
)