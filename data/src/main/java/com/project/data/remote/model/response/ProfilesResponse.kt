package com.project.data.remote.model.response

data class ProfilesResponse(
    val name: String,
    val likeRecipe: List<ProfileResponse>,
    val myRecipe: List<ProfileResponse>
)