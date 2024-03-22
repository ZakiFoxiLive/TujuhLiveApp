package com.tujuhlive.core.network.di

import com.tujuhlive.core.network.ApiService
import com.tujuhlive.core.network.dataProviders.VideoDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    fun provideApiService():ApiService{
        return Retrofit
            .Builder()
            .baseUrl("https://api-prd.tujuhlive.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideVideoDataProvider(apiService: ApiService):VideoDataProviders{
        return VideoDataProviders(apiService)
    }
}