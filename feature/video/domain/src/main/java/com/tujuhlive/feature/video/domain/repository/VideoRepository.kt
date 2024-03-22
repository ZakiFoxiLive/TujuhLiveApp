package com.tujuhlive.feature.video.domain.repository

import com.tujuhlive.feature.video.domain.model.VideoListResponse

interface VideoRepository {
    suspend fun getVideoList(language:String, uid: String, p: Int, refreshOrder: Int) : VideoListResponse
}