package com.project.presentation.viewmodel.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.profile.request.ProfileRequestModel
import com.project.domain.usecase.profile.RenameUserNameUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RenameUserNameViewModel @Inject constructor(
    private val renameUserNameUseCase: RenameUserNameUseCase
): ViewModel() {
    fun renameUserName(profileRequestModel: ProfileRequestModel) {
        viewModelScope.launch {
            renameUserNameUseCase(profileRequestModel)
                .onSuccess {
                    Log.d("renameUserName", it.toString())
                }
                .onFailure {
                    Log.d("renameUserName", it.message.toString())
                }
        }
    }
}