package com.project.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "local")

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
): LocalDataSource {
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val ACCESS_TOKEN_EXP = stringPreferencesKey("access_token_exp")
        private val REFRESH_TOKEN_EXP = stringPreferencesKey("refresh_token_exp")
    }

    override suspend fun saveToken(accessToken: String, refreshToken: String, accessTokenExp: String, refreshTokenExp: String) {
        context.dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
            it[ACCESS_TOKEN_EXP] = accessTokenExp
            it[REFRESH_TOKEN_EXP] = refreshTokenExp
        }
    }

    override fun getAccessToken(): Flow<String> = context.dataStore.data.map { it[ACCESS_TOKEN] ?: "" }

    override fun getRefreshToken(): Flow<String> = context.dataStore.data.map { it[REFRESH_TOKEN] ?: "" }

    override fun getAccessTokenExp(): Flow<String> = context.dataStore.data.map { it[ACCESS_TOKEN_EXP] ?: "" }

    override fun getRefreshTokenExp(): Flow<String> = context.dataStore.data.map { it[REFRESH_TOKEN_EXP] ?: "" }
}