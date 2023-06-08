package com.project.data.remote.datasource.auth

import com.project.data.remote.model.request.SignInRequest
import com.project.data.remote.model.request.SignUpRequest
import com.project.data.remote.model.response.SignInResponse
import com.project.data.remote.network.api.AuthApi
import com.project.data.remote.util.safeApiCall
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthDataSource {
    override suspend fun signIn(body: SignInRequest): SignInResponse = safeApiCall { authApi.signIn(body) }

    override suspend fun signUp(body: SignUpRequest) = safeApiCall { authApi.signUp(body) }
}