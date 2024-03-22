package com.tujuhlive.feature.video.domain.usecase

import com.tujuhlive.core.common.UiEvent
import com.tujuhlive.feature.video.domain.model.VideoListResponse
import com.tujuhlive.feature.video.domain.repository.VideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetVideoListUseCase @Inject constructor(private val videoRepository: VideoRepository){
    operator fun invoke(language: String, uid: String, p: Int, refreshOrder: Int) = flow<UiEvent<VideoListResponse>> {
        emit(UiEvent.Loading())
        emit(UiEvent.Success(videoRepository.getVideoList(language, uid, p, refreshOrder)))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}