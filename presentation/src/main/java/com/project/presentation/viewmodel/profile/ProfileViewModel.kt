package com.project.presentation.viewmodel.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.profile.request.ProfileImageRequestModel
import com.project.domain.model.profile.request.ProfileRequestModel
import com.project.domain.usecase.image.ImageUploadUseCase
import com.project.domain.usecase.profile.DeleteUserInfoUseCase
import com.project.domain.usecase.profile.GetUserInfoUseCase
import com.project.domain.usecase.profile.ModifyProfileImageUseCase
import com.project.domain.usecase.profile.RenameUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val renameUserNameUseCase: RenameUserNameUseCase,
    private val deleteUserInfoUseCase: DeleteUserInfoUseCase,
    private val modifyProfileImageUseCase: ModifyProfileImageUseCase,
    private val imageUploadUseCase: ImageUploadUseCase
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

    fun modifyProfileImage(profileImageRequestModel: ProfileImageRequestModel) {
        viewModelScope.launch {
            modifyProfileImageUseCase(profileImageRequestModel)
                .onSuccess {
                    Log.d("modifyProfileImage", it.toString())
                }
                .onFailure {
                    Log.d("modifyProfileImage", it.message.toString())
                }
        }
    }

    fun imageUpload(file: List<MultipartBody.Part>) {
        viewModelScope.launch {
            imageUploadUseCase(file)
                .onSuccess {
                    Log.d("imageUpload", it.toString())
                }
                .onFailure {
                    Log.d("imageUpload", it.message.toString())
                }
        }
    }
}