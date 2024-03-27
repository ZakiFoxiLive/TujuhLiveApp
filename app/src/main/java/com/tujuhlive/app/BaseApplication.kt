package com.tujuhlive.app

import android.app.Application
import com.tencent.rtmp.TXLiveBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initTencentSDK()
    }

    private fun initTencentSDK(){
        TXLiveBase.getInstance().setLicence(this, BuildConfig.liveLicenceUrl, BuildConfig.liveKey)
    }
}