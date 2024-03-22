package com.tujuhlive.core.network

import com.tujuhlive.core.network.model.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("appapi/?service=Videos.RetrieveList")
    suspend fun getVideoList(
        @Query("language") language: String,
        @Query("uid") uid: String,
        @Query("p") p: Int,
        @Query("refreshOrder") refreshOrder: Int
    ): VideoListResponse
}