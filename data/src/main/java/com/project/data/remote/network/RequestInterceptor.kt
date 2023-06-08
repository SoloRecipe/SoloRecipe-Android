package com.project.data.remote.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.project.data.BuildConfig
import com.project.data.local.datasource.LocalDataSource
import com.project.domain.exception.TokenExpiredException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    private val localDataSource: LocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        val ignorePath = listOf("/auth")

        ignorePath.forEach {
            if (path.contains(it)) {
                return chain.proceed(request)
            }
        }

        val accessToken = runBlocking { localDataSource.getAccessToken().first() }
        val refreshToken = runBlocking { localDataSource.getRefreshToken().first() }
        val currentTime = LocalDateTime.now()
        val accessTokenExp = runBlocking { convertDateFormat(localDataSource.getAccessTokenExp().first()) }
        val refreshTokenExp = runBlocking { convertDateFormat(localDataSource.getRefreshTokenExp().first()) }

        if (currentTime.isAfter(refreshTokenExp)) throw TokenExpiredException()

        if (currentTime.isAfter(accessTokenExp)) {
            val client = OkHttpClient()
            val reissueRequest = Request.Builder()
                .url("${BuildConfig.BASE_URL}auth")
                .patch(RequestBody.create(MediaType.parse("application/json"), JsonObject().toString()))
                .addHeader(
                    "refresh-token",
                    refreshToken
                )
                .build()
            val jsonParser = JsonParser()
            val response = client.newCall(reissueRequest).execute()
            if (response.isSuccessful) {
                val token = jsonParser.parse(response.body()!!.string()) as JsonObject
                runBlocking {
                    localDataSource.saveToken(
                        token["accessToken"].toString(),
                        token["refreshToken"].toString(),
                        token["accessExp"].toString(),
                        token["refreshExp"].toString()
                    )
                }
            } else throw TokenExpiredException()
        }

        return chain.proceed(
            request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        )
    }
}

fun convertDateFormat(input: String): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'T' HH:mm:ss")
    return LocalDateTime.parse(input, formatter)
}