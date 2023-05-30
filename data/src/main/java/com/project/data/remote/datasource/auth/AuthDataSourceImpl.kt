package com.project.data.remote.datasource.auth

import com.project.data.remote.model.request.SignInRequest
import com.project.data.remote.model.response.SignInResponse
import com.project.data.remote.network.AuthApi
import com.project.data.remote.util.safeApiCall
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthDataSource {
    override suspend fun signIn(body: SignInRequest): SignInResponse = safeApiCall { authApi.signIn(body) }
}