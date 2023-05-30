package com.project.domain.model.auth.request

data class SignUpRequestModel(
    val email: String,
    val password: String,
    val nickname: String
)