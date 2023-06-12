package com.project.presentation.viewmodel.modify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.recipe.request.RecipesRequestModel
import com.project.domain.usecase.recipe.DeleteRecipeUseCase
import com.project.domain.usecase.recipe.ModifyRecipeUseCase
import com.project.presentation.viewmodel.util.UiState
import com.project.presentation.viewmodel.util.exceptionHandling
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ModifyViewModel(
    private val modifyRecipeUseCase: ModifyRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase
): ViewModel() {
    private val _modifyUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val modifyUiState = _modifyUiState.asStateFlow()

    private val _deleteRecipeUiState: MutableStateFlow<UiState<Nothing>> = MutableStateFlow(UiState.Loading)
    val deleteRecipeUiState = _deleteRecipeUiState.asStateFlow()

    fun modifyRecipe(
        index:Long,
        body: RecipesRequestModel
    ) {
        viewModelScope.launch {
            modifyRecipeUseCase(index, body)
                .onSuccess {
                    _modifyUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _modifyUiState.value = UiState.BadRequest },
                        unauthorizedAction = { _modifyUiState.value = UiState.Unauthorized },
                        forbiddenAction = { _modifyUiState.value = UiState.Forbidden },
                        notFoundAction = { _modifyUiState.value = UiState.NotFound }
                    )
                }
        }
    }

    fun deleteRecipe(index: Long) {
        viewModelScope.launch {
            deleteRecipeUseCase(index)
                .onSuccess {
                    _deleteRecipeUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        unauthorizedAction = { _deleteRecipeUiState.value = UiState.Unauthorized },
                        forbiddenAction = { _deleteRecipeUiState.value = UiState.Forbidden },
                        notFoundAction = { _deleteRecipeUiState.value = UiState.NotFound }
                    )
                }
        }
    }
}