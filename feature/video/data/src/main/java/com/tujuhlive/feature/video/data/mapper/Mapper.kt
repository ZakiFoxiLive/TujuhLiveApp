package com.tujuhlive.feature.video.data.mapper

import com.tujuhlive.core.network.model.DataDTO
import com.tujuhlive.core.network.model.MusicInfoDTO
import com.tujuhlive.core.network.model.UserinfoDTO
import com.tujuhlive.core.network.model.VideoDTO
import com.tujuhlive.core.network.model.VideoListResponse
import com.tujuhlive.feature.video.domain.model.Data
import com.tujuhlive.feature.video.domain.model.MusicInfo
import com.tujuhlive.feature.video.domain.model.Userinfo
import com.tujuhlive.feature.video.domain.model.Video

fun VideoListResponse.toDomainVideoList():com.tujuhlive.feature.video.domain.model.VideoListResponse{
    return com.tujuhlive.feature.video.domain.model.VideoListResponse(
        this.data.toDomainData(),
        this.msg,
        this.ret
    )
}

fun DataDTO.toDomainData():Data{
    return Data(
        this.code,
        this.info.toDomainVideo(),
        this.msg
    )
}

fun List<VideoDTO>.toDomainVideo():List<Video>{
    return this.map {
        Video(
            it.ad_url,
            it.addtime,
            it.anyway,
            it.avatar,
            it.city,
            it.classid,
            it.comments,
            it.datetime,
            it.goods_type,
            it.goodsid,
            it.href,
            it.href_w,
            it.id,
            it.is_ad,
            it.isattent,
            it.islike,
            it.isstep,
            it.lat,
            it.likes,
            it.lng,
            it.music_id,
            it.music_info.toDomainMusicInfo(),
            it.recommend_time,
            it.saved,
            it.shares,
            it.status,
            it.steps,
            it.thumb,
            it.thumb_s,
            it.title,
            it.type,
            it.uid,
            it.user_nickname,
            it.userinfo.toDomainUserInfo(),
            it.views
        )
    }
}

fun MusicInfoDTO.toDomainMusicInfo():MusicInfo{
    return MusicInfo(
        this.img_url,
        this.title
    )
}

fun UserinfoDTO.toDomainUserInfo():Userinfo{
    return Userinfo(
        this.avatar,
        this.id,
        this.liveStatus,
        this.user_nickname,
        this.user_username
    )
}