package com.project.presentation.viewmodel.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.profile.DeleteUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeleteUserInfoViewModel @Inject constructor(
    private val deleteUserInfoUseCase: DeleteUserInfoUseCase
): ViewModel() {
    fun deleteUserInfo() {
        viewModelScope.launch {
            deleteUserInfoUseCase()
                .onSuccess {
                    Log.d("deleteUserInfo", it.toString())
                }
                .onFailure {
                    Log.d("deleteUserInfo", it.message.toString())
                }
        }
    }
}