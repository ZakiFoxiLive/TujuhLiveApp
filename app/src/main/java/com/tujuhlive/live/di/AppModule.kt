package com.tujuhlive.live.di

import com.tujuhlive.feature.video.ui.navigation.VideoApi
import com.tujuhlive.live.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideNavigationProvider(videoApi: VideoApi):NavigationProvider{
        return NavigationProvider(videoApi)
    }
}