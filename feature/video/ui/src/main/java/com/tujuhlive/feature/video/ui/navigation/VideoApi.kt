package com.tujuhlive.feature.video.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.tujuhlive.core.api.FeatureApi

interface VideoApi : FeatureApi {
}

class VideoApiImpl : VideoApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalVideoFeatureApi.registerGraph(
            navController, navGraphBuilder
        )
    }
}