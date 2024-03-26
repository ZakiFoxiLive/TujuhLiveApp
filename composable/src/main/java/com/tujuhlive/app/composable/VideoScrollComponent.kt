package com.tujuhlive.app.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXVodPlayConfig
import com.tencent.rtmp.TXVodPlayer
import com.tencent.rtmp.ui.TXCloudVideoView
import com.tujuhlive.feature.video.domain.model.Video

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoScrollLayout(
    videoList: List<Video>
){
    val pagerState = rememberPagerState {
        videoList.size
    }
    val fling = PagerDefaults.flingBehavior(
        state = pagerState, lowVelocityAnimationSpec = tween(
            easing = LinearEasing, durationMillis = 300
        )
    )

    VerticalPager(
        state = pagerState,
        flingBehavior = fling,
        beyondBoundsPageCount = 1,
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(factory = { context ->
            TXCloudVideoView(context).apply {
                val mPlayer = TXVodPlayer(context)
                val mPlayerConfig = TXVodPlayConfig()

                val HEADER: MutableMap<String, String> = HashMap()
                val host = "https://api-prd.tujuhlive.com"
                HEADER["referer"] = host
                mPlayerConfig.headers = HEADER
                mPlayerConfig.progressInterval = 50
                mPlayer.setConfig(mPlayerConfig)
                mPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN)
                mPlayer.isLoop = true
                mPlayer.setAutoPlay(true)
                mPlayer.setPlayerView(this)
                mPlayer.startVodPlay(videoList[it].href)
            }
        },
            modifier = Modifier.fillMaxSize())
    }
}