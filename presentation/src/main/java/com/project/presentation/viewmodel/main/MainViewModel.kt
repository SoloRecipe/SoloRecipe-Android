package com.project.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.domain.model.recipe.response.RecipeResponseModel
import com.project.domain.usecase.recipe.GetAllRecipesUseCase
import com.project.domain.usecase.recipe.GetRecommendRecipesUseCase
import com.project.domain.usecase.recipe.SearchRecipeUseCase
import com.project.presentation.viewmodel.util.UiState
import com.project.presentation.viewmodel.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val getRecommendRecipesUseCase: GetRecommendRecipesUseCase,
    private val searchRecipeUseCase: SearchRecipeUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<RecipeResponseModel>>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getRecommendRecipes(): Flow<PagingData<RecipeResponseModel>> = getRecommendRecipesUseCase().cachedIn(viewModelScope)

    fun getAllRecipes(): Flow<PagingData<RecipeResponseModel>> = getAllRecipesUseCase().cachedIn(viewModelScope)

    fun searchRecipe(name: String) {
        viewModelScope.launch {
            searchRecipeUseCase(name)
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = { _uiState.value = UiState.BadRequest },
                        unauthorizedAction = { _uiState.value = UiState.Unauthorized }
                    )
                }
        }
    }

}