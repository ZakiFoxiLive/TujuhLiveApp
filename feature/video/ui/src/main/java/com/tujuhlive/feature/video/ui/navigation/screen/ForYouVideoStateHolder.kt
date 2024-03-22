package com.tujuhlive.feature.video.ui.navigation.screen

import com.tujuhlive.feature.video.domain.model.VideoListResponse

data class ForYouVideoStateHolder (
    val isLoading: Boolean = false,
    val data: VideoListResponse? = null,
    val error: String = ""
)