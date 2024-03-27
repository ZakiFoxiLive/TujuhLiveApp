package com.tujuhlive.app.composable

import android.os.Bundle
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import com.tencent.rtmp.ITXVodPlayListener
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXVodPlayConfig
import com.tencent.rtmp.TXVodPlayer
import com.tencent.rtmp.ui.TXCloudVideoView
import com.tujuhlive.feature.video.domain.model.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoPlayer(
    video: Video,
    pagerState: PagerState,
    pageIndex: Int,
    onSingleTap: (exoPlayer: TXVodPlayer) -> Unit,
    onDoubleTap: (exoPlayer: TXVodPlayer, offset: Offset) -> Unit,
    onVideoDispose: () -> Unit = {},
    onVideoGoBackground: () -> Unit = {}
){
    val context = LocalContext.current
    var thumbnail by remember {
        mutableStateOf<Pair<String?, Boolean>>(Pair(null, true))
    }
    var isFirstFrameLoad = remember { false }

    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.IO) {
            val url = video.thumb
            withContext(Dispatchers.Main) {
                thumbnail = thumbnail.copy(first = url, second = thumbnail.second)
            }
        }
    }

    if (pagerState.settledPage == pageIndex) {
        val mPlayerConfig = TXVodPlayConfig()
        val HEADER: MutableMap<String, String> = HashMap()
        val host = "https://api-prd.tujuhlive.com"
        HEADER["referer"] = host
        mPlayerConfig.headers = HEADER
        mPlayerConfig.progressInterval = 50

        var mPlayer : TXVodPlayer? = remember(context) {
            TXVodPlayer(context).apply {
                setConfig(mPlayerConfig)
                setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN)
                isLoop = true
                setAutoPlay(true)
                setVodListener(object : ITXVodPlayListener{
                    override fun onPlayEvent(txVodPlayer: TXVodPlayer?, event: Int, bundle: Bundle?) {
                        when (event) {
                            TXLiveConstants.PLAY_EVT_PLAY_PROGRESS -> {

                            }
                            TXLiveConstants.PLAY_EVT_PLAY_BEGIN -> {

                            }
                            TXLiveConstants.PLAY_EVT_PLAY_LOADING -> {

                            }
                            TXLiveConstants.PLAY_EVT_PLAY_END -> {

                            }
                            TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME -> {
                                isFirstFrameLoad = true
                                thumbnail = thumbnail.copy(second = false)
                            }
                            TXLiveConstants.PLAY_EVT_CHANGE_RESOLUTION -> {

                            }
                        }
                    }

                    override fun onNetStatus(p0: TXVodPlayer?, p1: Bundle?) {

                    }

                })
            }
        }

        val lifecycleOwner by rememberUpdatedState(LocalLifecycleOwner.current)
        DisposableEffect(key1 = lifecycleOwner) {
            val lifeCycleObserver = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_STOP -> {
                        mPlayer!!.pause()
                        onVideoGoBackground()
                    }

                    Lifecycle.Event.ON_START -> mPlayer!!.resume()

                    Lifecycle.Event.ON_PAUSE -> mPlayer!!.pause()

                    Lifecycle.Event.ON_RESUME -> mPlayer!!.resume()
                    else -> {}
                }
            }
            lifecycleOwner.lifecycle.addObserver(lifeCycleObserver)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(lifeCycleObserver)
            }
        }

        val playerView = remember {
            TXCloudVideoView(context).apply {
                mPlayer!!.setPlayerView(this)
                mPlayer!!.startVodPlay(video.href)
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }

        DisposableEffect(key1 = AndroidView(factory = {
            playerView
        }, modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                onSingleTap(mPlayer!!)
            }, onDoubleTap = { offset ->
                onDoubleTap(mPlayer!!, offset)
            })
        }), effect = {
            onDispose {
                thumbnail = thumbnail.copy(second = true)
                mPlayer = null
                onVideoDispose()
            }
        })
    }

    if (thumbnail.second) {
        AsyncImage(
            model = thumbnail.first,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}