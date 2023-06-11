package com.project.domain.repository

interface LikeRepository {
    suspend fun likeRecipe(index: Long)
}