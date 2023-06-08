package com.project.data.remote.network.api

import com.project.data.remote.model.request.ProfileRequest
import com.project.data.remote.model.response.ProfilesResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH

interface ProfileApi {
    @GET("user")
    suspend fun getUserInfo(): ProfilesResponse

    @PATCH("user")
    suspend fun renameUserName(
        @Body body: ProfileRequest
    )

    @DELETE("user")
    suspend fun deleteUserInfo()
}