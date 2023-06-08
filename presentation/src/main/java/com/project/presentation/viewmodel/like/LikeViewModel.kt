package com.project.presentation.viewmodel.like

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.like.LikeRecipeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LikeViewModel @Inject constructor(
    private val likeRecipeUseCase: LikeRecipeUseCase
) : ViewModel() {
    fun likeRecipe() {
        viewModelScope.launch {
            likeRecipeUseCase()
                .onSuccess {
                    Log.d("likeRecipe", it.toString())
                }.onFailure {
                    Log.d("likeRecipe", it.message.toString())
                }
        }
    }
}