package com.example.adsxml.ads

import android.app.Activity
import com.example.core.FullScreenAdsShowListener
import com.example.core.ad_units.IshfaqInterstitialAd
import com.example.inter.InterstitialAdsManager

fun showInterAd(
    enable: Boolean,
    context: Activity,
    key: String,
    interAdsManager: InterstitialAdsManager,
    requestNewIfNotAvailable: Boolean = true,
    requestNewAfterShowing: Boolean = true,
    onAdClosed: (Boolean) -> Unit,
) {
    val controller = interAdsManager.getAdController(key)
    if (controller != null && enable) {
        val interAd = (controller.getAvailableAd() as? IshfaqInterstitialAd)
        if (interAd != null) {
            interAd.showInter(context = context, callBack = object : FullScreenAdsShowListener {
                override fun onAdDismiss() {
                    onAdClosed.invoke(true)
                    controller.destroyAd(context)
                    if (requestNewAfterShowing) {
                        controller.loadAd(context, null)
                    }
                }

                override fun onAdShownFailed() {
                    onAdClosed.invoke(false)
                }
            })
        } else {
            if (requestNewIfNotAvailable) {
                controller.loadAd(context, null)
            }
            onAdClosed.invoke(false)
        }
    } else {
        onAdClosed.invoke(false)
    }
}