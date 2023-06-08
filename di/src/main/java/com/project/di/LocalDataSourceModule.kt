package com.project.di

import com.project.data.local.datasource.LocalDataSource
import com.project.data.local.datasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {
    @Binds
    fun bindsLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
}