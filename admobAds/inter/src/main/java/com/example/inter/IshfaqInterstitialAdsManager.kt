package com.example.inter

import android.app.Activity
import com.example.core.AdsController
import com.example.core.AdsManager
import com.example.core.FullScreenAdsShowListener
import com.example.core.ad_units.IshfaqInterstitialAd

class IshfaqInterstitialAdsManager : AdsManager {

    private val adsMap = HashMap<String, IshfaqInterstitialAdsController>()


    override fun getAdController(key: String): AdsController? {
        return adsMap[key]
    }

    override fun addNewController(adKey: String, adId: String) {
        val controller = adsMap[adKey]
        if (controller == null) {
            adsMap[adKey] = IshfaqInterstitialAdsController(adId, adId)
        }
    }

    fun tryShowingInterstitialAd(
        enable: Boolean,
        key: String,
        context: Activity,
        requestNewIfNotAvailable: Boolean = true,
        requestNewIfAdShown: Boolean = true,
        onAdDismiss: (Boolean) -> Unit
    ) {
        if (enable.not()) {
            onAdDismiss.invoke(false)
            return
        }
        val controller = getAdController(key)
        if (controller == null) {
            onAdDismiss.invoke(false)
            return
        }
        val adToShow = (controller.getAvailableAd() as? IshfaqInterstitialAd)
        if (adToShow != null) {
            adToShow.showInter(context, object : FullScreenAdsShowListener {
                override fun onAdDismiss() {
                    super.onAdDismiss()
                    onAdDismiss.invoke(true)
                    controller.destroyAd(context)
                    if (requestNewIfAdShown) {
                        controller.loadAd(context, null)
                    }
                }

                override fun onAdShownFailed() {
                    super.onAdShownFailed()
                    onAdDismiss.invoke(false)
                }
            })
        } else {
            if (requestNewIfNotAvailable) {
                controller.loadAd(context, null)
            }
            onAdDismiss.invoke(false)
        }
    }

}