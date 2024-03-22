package com.tujuhlive.feature.video.domain.di

import com.tujuhlive.feature.video.domain.repository.VideoRepository
import com.tujuhlive.feature.video.domain.usecase.GetVideoListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainLayerModule {
    @Provides
    fun provideGetVideoListUseCase(videoRepository: VideoRepository):GetVideoListUseCase{
        return GetVideoListUseCase(videoRepository)
    }
}