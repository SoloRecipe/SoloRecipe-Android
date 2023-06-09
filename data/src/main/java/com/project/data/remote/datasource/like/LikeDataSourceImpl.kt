package com.project.data.remote.datasource.like

import com.project.data.remote.network.api.LikeApi
import com.project.data.remote.util.safeApiCall
import javax.inject.Inject

class LikeDataSourceImpl @Inject constructor(
    private val likeApi: LikeApi
): LikeDataSource {
    override suspend fun likeRecipe(index: Long) = safeApiCall { likeApi.likeRecipe(index) }

    override suspend fun unlikeRecipe(index: Long) = safeApiCall { likeApi.unlikeRecipe(index) }
}