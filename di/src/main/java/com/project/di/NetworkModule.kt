package com.project.di

import com.project.data.remote.network.AuthApi
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

object NetworkModule {
    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}