package com.tujuhlive.app

import android.app.Application
import com.tencent.live.TXLiveBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initTencentSDK()
    }

    private fun initTencentSDK(){
        val liveLicenceUrl = "https://license.vod-control.com/license/v2/1324250441_1/v_cube.license"
        val liveKey = "464b08e94f3af0bf630e1c7c86a53364"
        val ugcLicenceUrl = "https://license.vod-control.com/license/v2/1324250441_1/v_cube.license"
        val ugcKey = "464b08e94f3af0bf630e1c7c86a53364"
        TXLiveBase.getInstance().setLicence(this, liveLicenceUrl, liveKey, ugcLicenceUrl, ugcKey)
    }
}