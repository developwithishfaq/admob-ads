package com.example.banner_ads

import android.app.Activity
import com.example.core.AdsController
import com.example.core.AdsLoadingStatusListener
import com.example.core.ad_units.IshfaqBannerAd
import com.example.core.ad_units.core.AdUnit
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

class AdmobBannerAdsController(
    private val adKey: String,
    private val adId: String,
) : AdsController {

    private var canRequestAd = true
    private var adEnabled = true
    private var currentBannerAd: IshfaqBannerAd? = null
    private var bannerAdSize: BannerAdSizes = BannerAdSizes.Banner
    private var adSize: AdSize = AdSize.BANNER

    private var listener: AdsLoadingStatusListener? = null

    fun setAdSize(size: BannerAdSizes) {
        bannerAdSize = size
        this.adSize = when (size) {
            BannerAdSizes.Banner -> {
                AdSize.BANNER
            }

            BannerAdSizes.FullBanner -> {
                AdSize.FULL_BANNER
            }

            BannerAdSizes.LargeBanner -> {
                AdSize.LARGE_BANNER
            }

            BannerAdSizes.MediumRectangle -> {
                AdSize.MEDIUM_RECTANGLE
            }

            BannerAdSizes.CollapsibleTop -> {
                AdSize.BANNER
            }

            BannerAdSizes.CollapsibleBottom -> {
                AdSize.BANNER
            }
        }
    }

    override fun setAdEnabled(enabled: Boolean) {
        adEnabled = enabled
    }

    override fun loadAd(context: Activity, callback: AdsLoadingStatusListener?) {
        listener = callback
        val adView = AdView(context)
        adView.setAdSize(adSize)
        adView.adUnitId = adId.ifBlank { "ca-app-pub-3940256099942544/9214589741" }

        // Create an ad request.
        val adRequest = AdRequest.Builder().build()

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                if (listener != null) {
                    canRequestAd = true
                    currentBannerAd = AdmobBannerAd(adView)
                    listener?.onAdLoaded()
                } else {
                    currentBannerAd?.destroyAd()
                    currentBannerAd = null
                }
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                super.onAdFailedToLoad(error)
                listener?.onAdFailedToLoad(error.message, error.code)
            }
        }
        // Start loading the ad in the background.
        adView.loadAd(adRequest)
    }

    override fun resetListener(context: Activity) {
        listener = null
    }

    override fun destroyAd(context: Activity) {
        currentBannerAd?.destroyAd()
        currentBannerAd = null
        canRequestAd = true
    }

    override fun getAdKey(): String {
        return adKey
    }

    override fun isAdAvailable(): Boolean {
        return currentBannerAd != null
    }

    override fun isAdRequesting(): Boolean {
        return canRequestAd.not()
    }

    override fun isAdAvailableOrRequesting(): Boolean {
        return isAdRequesting() || isAdAvailable()
    }

    override fun getAvailableAd(): AdUnit? {
        return currentBannerAd
    }
}