package com.tujuhlive.feature.video.data.repository

import com.tujuhlive.core.network.dataProviders.VideoDataProviders
import com.tujuhlive.feature.video.data.mapper.toDomainVideoList
import com.tujuhlive.feature.video.domain.model.VideoListResponse
import com.tujuhlive.feature.video.domain.repository.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(private val videoDataProviders: VideoDataProviders) : VideoRepository {
    override suspend fun getVideoList(
        language: String,
        uid: String,
        p: Int,
        refreshOrder: Int
    ): VideoListResponse {
        return videoDataProviders.getVideoList(language, uid, p, refreshOrder).toDomainVideoList()
    }
}