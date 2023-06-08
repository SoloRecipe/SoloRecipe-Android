package com.project.di

import com.project.data.remote.datasource.auth.AuthDataSource
import com.project.data.remote.datasource.auth.AuthDataSourceImpl
import com.project.data.remote.datasource.image.ImageDataSource
import com.project.data.remote.datasource.image.ImageDataSourceImpl
import com.project.data.remote.datasource.like.LikeDataSource
import com.project.data.remote.datasource.like.LikeDataSourceImpl
import com.project.data.remote.datasource.profile.ProfileDataSource
import com.project.data.remote.datasource.profile.ProfileDataSourceImpl
import com.project.data.remote.datasource.recipe.RecipeDataSource
import com.project.data.remote.datasource.recipe.RecipeDataSourceImpl
import com.project.data.remote.datasource.review.ReviewDataSource
import com.project.data.remote.datasource.review.ReviewDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {
    @Binds
    fun bindsAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    fun bindsRecipeDataSource(
        recipeDataSourceImpl: RecipeDataSourceImpl
    ): RecipeDataSource

    @Binds
    fun bindsProfileDataSource(
        profileDataSourceImpl: ProfileDataSourceImpl
    ): ProfileDataSource

    @Binds
    fun bindsReviewDataSource(
        reviewDataSourceImpl: ReviewDataSourceImpl
    ): ReviewDataSource

    @Binds
    fun bindsLikeDataSource(
        likeDataSourceImpl: LikeDataSourceImpl
    ): LikeDataSource

    @Binds
    fun bindsImageDataSource(
        imageDataSourceImpl: ImageDataSourceImpl
    ): ImageDataSource
}