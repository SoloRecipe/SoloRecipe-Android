package com.project.di

import com.project.data.repository.AuthRepositoryImpl
import com.project.data.repository.ImageRepositoryImpl
import com.project.data.repository.LikeRepositoryImpl
import com.project.data.repository.ProfileRepositoryImpl
import com.project.data.repository.RecipeRepositoryImpl
import com.project.data.repository.ReviewRepositoryImpl
import com.project.domain.repository.AuthRepository
import com.project.domain.repository.ImageRepository
import com.project.domain.repository.LikeRepository
import com.project.domain.repository.ProfileRepository
import com.project.domain.repository.RecipeRepository
import com.project.domain.repository.ReviewRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindsProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    fun bindsRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ): RecipeRepository

    @Binds
    fun bindsReviewRepository(
        reviewRepositoryImpl: ReviewRepositoryImpl
    ): ReviewRepository

    @Binds
    fun bindsLikeRepository(
        likeRepositoryImpl: LikeRepositoryImpl
    ): LikeRepository

    @Binds
    fun bindsImageRepository(
        imageRepositoryImpl: ImageRepositoryImpl
    ): ImageRepository
}