package com.project.data.remote.datasource.auth

import com.project.data.remote.model.request.SignInRequest
import com.project.data.remote.model.request.SignUpRequest
import com.project.data.remote.model.response.SignInResponse

interface AuthDataSource {
    suspend fun signIn(body: SignInRequest): SignInResponse

    suspend fun signUp(body: SignUpRequest)
}