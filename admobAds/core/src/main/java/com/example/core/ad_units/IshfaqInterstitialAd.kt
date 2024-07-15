package com.example.core.ad_units

import android.app.Activity
import com.example.core.FullScreenAdsShowListener
import com.example.core.ad_units.core.AdUnit

interface IshfaqInterstitialAd : AdUnit {
    fun showInter(context: Activity, callBack: FullScreenAdsShowListener)
    fun destroyAd()
}