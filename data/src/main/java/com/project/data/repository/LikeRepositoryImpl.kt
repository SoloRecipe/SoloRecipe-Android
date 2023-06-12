package com.project.data.repository

import com.project.data.remote.datasource.like.LikeDataSource
import com.project.domain.repository.LikeRepository
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val likeDataSource: LikeDataSource
): LikeRepository {
    override suspend fun likeRecipe(index: Long) = likeDataSource.likeRecipe(index)
    override suspend fun unlikeRecipe(index: Long) = likeDataSource.unlikeRecipe(index)
}