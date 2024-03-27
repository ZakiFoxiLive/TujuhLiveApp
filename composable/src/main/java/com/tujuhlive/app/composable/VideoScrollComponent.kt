package com.tujuhlive.app.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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

        var pauseButtonVisibility by remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize()) {
            VideoPlayer(
                video = videoList[it],
                pagerState = pagerState,
                pageIndex = it,
                onSingleTap = { txVodPlayer ->
                    if (txVodPlayer.isPlaying) {
                        txVodPlayer.pause()
                        pauseButtonVisibility = true
                    }
                    else {
                        txVodPlayer.resume()
                        pauseButtonVisibility = false
                    }
                },
                onDoubleTap = { txVodPlayer, offset -> },
                onVideoDispose = { pauseButtonVisibility = false },
                onVideoGoBackground = { pauseButtonVisibility = false }
            )

            AnimatedVisibility(
                visible = pauseButtonVisibility,
                enter = scaleIn(spring(Spring.DampingRatioMediumBouncy), initialScale = 1.5f),
                exit = scaleOut(tween(150)),
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(70.dp)
                )
            }
        }
    }
}