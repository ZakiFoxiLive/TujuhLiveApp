package com.tujuhlive.feature.video.ui.navigation.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tujuhlive.live.composable.VideoScrollLayout

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
                VideoScrollLayout(
                    videoList = video.data.info
                )
            }
        }
    }
}