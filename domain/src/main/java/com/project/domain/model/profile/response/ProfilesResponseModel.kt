package com.project.domain.model.profile.response

data class ProfilesResponseModel(
    val name: String,
    val likeRecipe: List<ProfileResponseModel>,
    val myRecipe: List<ProfileResponseModel>
)

