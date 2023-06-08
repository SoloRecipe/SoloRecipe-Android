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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val getRecommendRecipesUseCase: GetRecommendRecipesUseCase,
    private val searchRecipeUseCase: SearchRecipeUseCase,
) : ViewModel() {
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
                    Log.d("searchRecipe", it.toString())
                }.onFailure {
                    Log.d("searchRecipe", it.message.toString())
                }
        }
    }

}