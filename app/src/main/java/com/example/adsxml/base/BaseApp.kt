package com.example.adsxml.base

import android.app.Activity
import com.example.adsxml.di.SharedModule
import com.example.app_open.AdmobAppOpenAd
import com.example.app_open.IshfaqAppOpenAdsManager
import com.example.app_open.IshfaqBaseApp
import com.example.core.FullScreenAdsShowListener
import com.example.core.commons.IshfaqConfigs
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : IshfaqBaseApp() {

    private val appOpenAdsManager: IshfaqAppOpenAdsManager by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(SharedModule)
            androidContext(applicationContext)
        }
        appOpenAdsManager.addNewController("MainAppOpen", IshfaqConfigs.TestAppOpenId)
        initAppOpenAds(appOpenAdsManager)
    }

    override fun onShowAppOpenAd(activity: Activity) {
        val controller = appOpenAdsManager.getAdController("MainAppOpen")
        controller?.let { controller ->
            if (controller.isAdAvailable()) {
                (controller.getAvailableAd() as? AdmobAppOpenAd)?.showAppOpen(
                    context = activity,
                    callBack = object : FullScreenAdsShowListener {
                        override fun onAdShown() {

                        }

                        override fun onAdDismiss() {
                            // Destroy Shown Ad
                            controller.destroyAd(activity)
                            controller.loadAd(activity,null)
                        }
                    }
                )
            } else {
                controller.loadAd(activity, null)
            }
        }
    }

    override fun canShowAppOpenAd(activity: Activity): Boolean {
        return true
    }
}