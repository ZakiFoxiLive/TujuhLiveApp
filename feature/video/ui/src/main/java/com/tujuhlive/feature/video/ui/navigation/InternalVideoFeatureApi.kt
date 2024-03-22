package com.tujuhlive.feature.video.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tujuhlive.core.api.FeatureApi
import com.tujuhlive.core.common.constants.VideoForYouFeature
import com.tujuhlive.feature.video.ui.navigation.screen.ForYouVideoScreen
import com.tujuhlive.feature.video.ui.navigation.screen.ForYouVideoViewModel

internal object InternalVideoFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = VideoForYouFeature.videoForYouScreen,
            route = VideoForYouFeature.nestedRoute
        ){
            composable(route = VideoForYouFeature.videoForYouScreen){
                val viewModel = hiltViewModel<ForYouVideoViewModel>()
                ForYouVideoScreen(viewModel = viewModel)
            }
        }
    }
}