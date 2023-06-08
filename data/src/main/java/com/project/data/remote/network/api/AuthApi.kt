package com.project.data.remote.network.api

import com.project.data.remote.model.request.SignInRequest
import com.project.data.remote.model.request.SignUpRequest
import com.project.data.remote.model.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun signIn(
        @Body body: SignInRequest
    ): SignInResponse

    @POST("auth/register")
    suspend fun signUp(
        @Body body: SignUpRequest
    )
}