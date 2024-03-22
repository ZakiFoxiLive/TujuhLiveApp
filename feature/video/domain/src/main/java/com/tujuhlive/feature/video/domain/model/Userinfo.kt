package com.tujuhlive.feature.video.domain.model

data class Userinfo(
    val avatar: String,
    val id: String,
    val liveStatus: Boolean,
    val user_nickname: String,
    val user_username: String
)