package com.project.presentation.viewmodel.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.profile.GetUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetUserInfoViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
): ViewModel() {
    fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase()
                .onSuccess {
                    Log.d("getUserInfo", it.toString())
                }
                .onFailure {
                    Log.d("getUserInfo", it.message.toString())
                }
        }
    }
}