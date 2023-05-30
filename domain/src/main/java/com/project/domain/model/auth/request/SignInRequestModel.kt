package com.project.domain.model.auth.request

data class SignInRequestModel(
    val email: String,
    val password: String,
)