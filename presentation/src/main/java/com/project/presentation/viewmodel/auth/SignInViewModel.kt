package com.project.presentation.viewmodel.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.auth.request.SignInRequestModel
import com.project.domain.usecase.auth.SaveTokenUseCase
import com.project.domain.usecase.auth.SignInUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {
    fun signIn(signInRequestModel: SignInRequestModel) {
        viewModelScope.launch {
            signInUseCase(signInRequestModel)
                .onSuccess {
                    Log.d("signIn", it.toString())
                    saveTokenUseCase(
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        accessTokenExp = it.accessExp,
                        refreshTokenExp = it.refreshExp
                    )
                }.onFailure {
                    Log.d("signIn", it.message.toString())
                }
        }
    }
}