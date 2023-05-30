package com.project.data.repository

import com.project.data.remote.datasource.auth.AuthDataSource
import com.project.data.remote.model.request.asSignInRequest
import com.project.data.remote.model.request.asSignUpRequest
import com.project.data.remote.model.response.asSignInResponseModel
import com.project.domain.model.auth.request.SignInRequestModel
import com.project.domain.model.auth.request.SignUpRequestModel
import com.project.domain.model.auth.response.SignInResponseModel
import com.project.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun signIn(signInRequestModel: SignInRequestModel): SignInResponseModel =
        authDataSource.signIn(signInRequestModel.asSignInRequest()).asSignInResponseModel()

    override suspend fun signUp(signUpRequestModel: SignUpRequestModel) =
        authDataSource.signUp(signUpRequestModel.asSignUpRequest())

}