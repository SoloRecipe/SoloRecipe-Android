package com.project.data.remote.datasource.like

interface LikeDataSource {
    suspend fun likeRecipe(index: Long)

    suspend fun unlikeRecipe(index: Long)
}