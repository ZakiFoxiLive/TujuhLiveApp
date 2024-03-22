package com.tujuhlive.core.network.dataProviders

import com.tujuhlive.core.network.ApiService
import javax.inject.Inject

class VideoDataProviders @Inject constructor(private val apiService: ApiService) {
    suspend fun getVideoList(language:String, uid: String, p: Int, refreshOrder: Int)
        = apiService.getVideoList(language, uid, p, refreshOrder)
}