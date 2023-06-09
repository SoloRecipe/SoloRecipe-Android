package com.project.presentation.viewmodel.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.profile.request.ProfileImageRequestModel
import com.project.domain.model.profile.request.ProfileRequestModel
import com.project.domain.model.profile.response.ProfilesResponseModel
import com.project.domain.usecase.image.ImageUploadUseCase
import com.project.domain.usecase.profile.DeleteUserInfoUseCase
import com.project.domain.usecase.profile.GetUserInfoUseCase
import com.project.domain.usecase.profile.ModifyProfileImageUseCase
import com.project.domain.usecase.profile.RenameUserNameUseCase
import com.project.presentation.viewmodel.util.UiState
import com.project.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val _userUiState: MutableStateFlow<UiState<ProfilesResponseModel>> = MutableStateFlow(UiState.Loading)
    val userUiState = _userUiState.asStateFlow()

    private val _renameUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val renameUiState = _renameUiState.asStateFlow()

    private val _deleteUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val deleteUiState = _deleteUiState.asStateFlow()

    private val _modifyUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val modifyUiState = _modifyUiState.asStateFlow()
    fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase()
                .onSuccess {
                    _userUiState.value = UiState.Success(it)
                }
                .onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = { _userUiState.value = UiState.Unauthorized }
                    )
                }
        }
    }

    fun renameUserName(profileRequestModel: ProfileRequestModel) {
        viewModelScope.launch {
            renameUserNameUseCase(profileRequestModel)
                .onSuccess {
                    _renameUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = { _renameUiState.value = UiState.Unauthorized },
                        notFoundAction = { _renameUiState.value = UiState.NotFound }
                    )
                }
        }
    }

    fun deleteUserInfo() {
        viewModelScope.launch {
            deleteUserInfoUseCase()
                .onSuccess {
                    _deleteUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = { _deleteUiState.value = UiState.Unauthorized },
                        notFoundAction = { _deleteUiState.value = UiState.NotFound }
                    )
                }
        }
    }

    fun modifyProfileImage(profileImageRequestModel: ProfileImageRequestModel) {
        viewModelScope.launch {
            modifyProfileImageUseCase(profileImageRequestModel)
                .onSuccess {
                    _modifyUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = { _modifyUiState.value = UiState.Unauthorized },
                        notFoundAction = { _modifyUiState.value = UiState.NotFound }
                    )
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