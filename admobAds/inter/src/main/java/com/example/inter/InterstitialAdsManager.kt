package com.example.inter

import com.example.core.AdsController
import com.example.core.AdsManager

class InterstitialAdsManager : AdsManager {

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

}