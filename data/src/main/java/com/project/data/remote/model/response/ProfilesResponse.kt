package com.project.data.remote.model.response

import com.project.domain.model.profile.response.ProfilesResponseModel

data class ProfilesResponse(
    val name: String,
    val likeRecipe: List<ProfileResponse>,
    val myRecipe: List<ProfileResponse>
)

fun ProfilesResponse.asProfilesResponseModel() = ProfilesResponseModel(
    name = name,
    likeRecipe = likeRecipe.map { it.asProfileResponseModel() },
    myRecipe = myRecipe.map { it.asProfileResponseModel() }
)