package com.project.data.repository

import com.project.data.remote.datasource.like.LikeDataSource
import com.project.domain.repository.LikeRepository
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val likeDataSource: LikeDataSource
): LikeRepository {
    override suspend fun likeRecipe() = likeDataSource.likeRecipe()
}