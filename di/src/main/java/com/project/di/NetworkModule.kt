package com.project.di

import com.project.data.remote.network.AuthApi
import com.project.data.remote.network.ProfileApi
import com.project.data.remote.network.RecipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideRecipeService(retrofit: Retrofit): RecipeApi = retrofit.create(RecipeApi::class.java)

    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileApi = retrofit.create(ProfileApi::class.java)
}