package com.project.data.remote.network

import com.project.data.remote.model.request.SignInRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun signIn(
        @Body body: SignInRequest
    )
}