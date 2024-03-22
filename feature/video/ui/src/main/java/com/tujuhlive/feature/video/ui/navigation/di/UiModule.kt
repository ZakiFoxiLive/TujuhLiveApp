package com.tujuhlive.feature.video.ui.navigation.di

import com.tujuhlive.feature.video.ui.navigation.VideoApi
import com.tujuhlive.feature.video.ui.navigation.VideoApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {
    @Provides
    fun provideVideoApi():VideoApi{
        return VideoApiImpl()
    }
}