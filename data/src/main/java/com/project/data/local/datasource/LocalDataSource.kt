package com.project.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveToken(
        accessToken: String,
        refreshToken: String,
        accessTokenExp: String,
        refreshTokenExp: String
    )

    fun getAccessToken(): Flow<String>

    fun getRefreshToken(): Flow<String>

    fun getAccessTokenExp(): Flow<String>

    fun getRefreshTokenExp(): Flow<String>
}