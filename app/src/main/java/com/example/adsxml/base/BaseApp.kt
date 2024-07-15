package com.example.adsxml.base

import android.app.Activity
import com.example.adsxml.di.SharedModule
import com.example.app_open.AdmobAppOpenAd
import com.example.app_open.AppOpenAdsManager
import com.example.app_open.IshfaqBaseApp
import com.example.app_open.di.AppOpenModules
import com.example.core.FullScreenAdsShowListener
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : IshfaqBaseApp() {

    private val appOpenAdsManager: AppOpenAdsManager by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(SharedModule + AppOpenModules)
            androidContext(applicationContext)
        }
        appOpenAdsManager.addNewController("MainAppOpen", "ca-app-pub-3940256099942544/9257395921")
        initBase()
    }

    override fun onShowAppOpenAd(activity: Activity) {
        appOpenAdsManager.getAdController("MainAppOpen")?.let { controller ->
            if (controller.isAdAvailable()) {
                (controller.getAvailableAd() as? AdmobAppOpenAd)?.showInter(
                    context = activity,
                    callBack = object : FullScreenAdsShowListener {
                        override fun onAdShown() {

                        }

                        override fun onAdDismiss() {
                            controller.destroyAd(activity)
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