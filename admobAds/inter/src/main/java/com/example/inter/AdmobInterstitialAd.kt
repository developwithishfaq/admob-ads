package com.example.inter

import android.app.Activity
import com.example.core.FullScreenAdsShowListener
import com.example.core.ad_units.IshfaqInterstitialAd
import com.example.core.ad_units.core.AdType
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.interstitial.InterstitialAd

class AdmobInterstitialAd(
    private val interstitialAds: InterstitialAd
) : IshfaqInterstitialAd {


    override fun showInter(context: Activity, callBack: FullScreenAdsShowListener) {
        interstitialAds.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                super.onAdFailedToShowFullScreenContent(p0)
                callBack.onAdShownFailed()
            }

            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()
                callBack.onAdShown()
            }

            override fun onAdClicked() {
                super.onAdClicked()
                callBack.onAdClick()
            }

            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                callBack.onAdDismiss()
            }
        }
        interstitialAds.show(context)
    }

    override fun destroyAd() {
    }


    override fun getAdType(): AdType {
        return AdType.INTERSTITIAL
    }
}