package com.project.presentation.viewmodel.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.domain.model.auth.response.RecipeResponseModel
import com.project.domain.usecase.recipe.GetAllRecipesUseCase
import com.project.domain.usecase.recipe.GetRecommendRecipesUseCase
import com.project.domain.usecase.recipe.SearchRecipeUseCase
import com.project.presentation.view.main.ALL
import com.project.presentation.view.main.RECOMMEND
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
    private val _uiState: MutableStateFlow<UiState<RecipeResponseModel>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()
    fun getRecipes(type: Int = ALL): Flow<PagingData<RecipeResponseModel>> {
        val recipes = if (type == RECOMMEND) {
            getRecommendRecipesUseCase().cachedIn(viewModelScope)
        } else {
            getAllRecipesUseCase().cachedIn(viewModelScope)
        }
        Log.d("recipes", recipes.toString())
        return recipes
    }

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