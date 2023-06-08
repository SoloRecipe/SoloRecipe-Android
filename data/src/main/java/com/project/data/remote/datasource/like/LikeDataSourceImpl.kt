package com.project.data.remote.datasource.like

import com.project.data.remote.network.api.LikeApi
import com.project.data.remote.util.safeApiCall
import javax.inject.Inject

class LikeDataSourceImpl @Inject constructor(
    private val likeApi: LikeApi
): LikeDataSource {
    override suspend fun likeRecipe() = safeApiCall { likeApi.likeRecipe() }
}