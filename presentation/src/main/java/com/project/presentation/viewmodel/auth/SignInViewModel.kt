package com.project.presentation.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.auth.request.SignInRequestModel
import com.project.domain.usecase.auth.SaveTokenUseCase
import com.project.domain.usecase.auth.SignInUseCase
import com.project.presentation.viewmodel.util.UiState
import com.project.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()
    fun signIn(signInRequestModel: SignInRequestModel) {
        viewModelScope.launch {
            signInUseCase(signInRequestModel)
                .onSuccess {
                    saveTokenUseCase(
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        accessTokenExp = it.accessExp,
                        refreshTokenExp = it.refreshExp
                    )
                    _uiState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _uiState.value = UiState.BadRequest },
                        notFoundAction = { _uiState.value = UiState.NotFound }
                    )
                }
        }
    }
}