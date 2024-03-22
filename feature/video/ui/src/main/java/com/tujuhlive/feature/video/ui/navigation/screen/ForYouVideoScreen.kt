package com.tujuhlive.feature.video.ui.navigation.screen

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.tujuhlive.feature.video.domain.model.Data

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ForYouVideoScreen(viewModel: ForYouVideoViewModel) {
    val result = viewModel.videoList.value
    val language = viewModel.language.collectAsState()
    val uid = viewModel.uid.collectAsState()
    val p = viewModel.p.collectAsState()
    val refreshOrder = viewModel.refreshOrder.collectAsState()

    Scaffold (modifier = Modifier.fillMaxSize()){
        Log.d("TAG", "ForYouScreen: ${it}")

        if(result.isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        if(result.error.isNotBlank()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = result.error)
            }
        }
        result.data?.let { video ->
            if(video.ret != 200){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error = " + video.msg)
                }
            } else if (video.data.info.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Nothing Found")
                }
            } else {
                val data = video.data.info
                val pagerState = rememberPagerState {
                    video.data.info.size
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
                    Image(
                        painter = rememberAsyncImagePainter(data[pagerState.currentPage].thumb_s),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}