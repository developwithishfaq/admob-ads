package com.example.banner_ads

import com.example.core.AdsController
import com.example.core.AdsManager

class IshfaqBannerAdsManager : AdsManager {

    private val adsMap = HashMap<String, AdmobBannerAdsController>()

    override fun getAdController(key: String): AdsController? {
        return adsMap[key]
    }

    override fun addNewController(adKey: String, adId: String) {
        val controller = adsMap[adKey]
        if (controller == null) {
            adsMap[adKey] = AdmobBannerAdsController(adId, adId)
        }
    }
}