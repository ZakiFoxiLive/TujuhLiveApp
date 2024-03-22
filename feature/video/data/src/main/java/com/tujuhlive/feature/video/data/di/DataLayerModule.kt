package com.tujuhlive.feature.video.data.di

import com.tujuhlive.core.network.dataProviders.VideoDataProviders
import com.tujuhlive.feature.video.data.repository.VideoRepositoryImpl
import com.tujuhlive.feature.video.domain.repository.VideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {
    @Provides
    fun provideVideoRepository(videoDataProviders: VideoDataProviders):VideoRepository{
        return VideoRepositoryImpl(videoDataProviders)
    }
}