package com.tujuhlive.live.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tujuhlive.core.common.constants.VideoForYouFeature

@Composable
fun AppNavGraph(navHostController: NavHostController, navigationProvider: NavigationProvider){
    NavHost(navController = navHostController, startDestination = VideoForYouFeature.nestedRoute){
        navigationProvider.videoApi.registerGraph(
            navHostController, this
        )
    }
}