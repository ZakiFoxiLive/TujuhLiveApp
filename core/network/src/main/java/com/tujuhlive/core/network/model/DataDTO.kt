package com.tujuhlive.core.network.model

data class DataDTO(
    val code: Int,
    val info: List<VideoDTO>,
    val msg: String
)