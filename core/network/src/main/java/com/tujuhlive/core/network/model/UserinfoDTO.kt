package com.tujuhlive.core.network.model

data class UserinfoDTO(
    val avatar: String,
    val id: String,
    val liveStatus: Boolean,
    val user_nickname: String,
    val user_username: String
)