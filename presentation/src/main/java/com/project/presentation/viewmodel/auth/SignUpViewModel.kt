package com.project.presentation.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.auth.request.SignUpRequestModel
import com.project.domain.usecase.auth.SignUpUseCase
import com.project.presentation.viewmodel.util.UiState
import com.project.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()
    fun signUp(signUpRequestModel: SignUpRequestModel) {
        viewModelScope.launch {
            signUpUseCase(signUpRequestModel)
                .onSuccess {
                    _uiState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _uiState.value = UiState.BadRequest },
                        conflictAction = { _uiState.value = UiState.Conflict }
                    )
                }
        }
    }
}