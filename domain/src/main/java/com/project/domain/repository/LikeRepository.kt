package com.project.domain.repository

interface LikeRepository {
    suspend fun likeRecipe(index: Long)

    suspend fun unlikeRecipe(index: Long)
}