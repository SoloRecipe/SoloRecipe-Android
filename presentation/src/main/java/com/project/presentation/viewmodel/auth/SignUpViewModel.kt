package com.project.presentation.viewmodel.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.auth.request.SignUpRequestModel
import com.project.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): ViewModel() {
    fun signUp(signUpRequestModel: SignUpRequestModel) {
        viewModelScope.launch {
            signUpUseCase(signUpRequestModel)
                .onSuccess {
                    Log.d("signUp", it.toString())
                }.onFailure {
                    Log.d("signUp", it.message.toString())
                }
        }
    }
}