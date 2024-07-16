package com.example.core.ad_units

import android.app.Activity
import com.example.core.FullScreenAdsShowListener
import com.example.core.ad_units.core.AdUnit

interface IshfaqAppOpenAd : AdUnit {
    fun showAppOpen(context: Activity, callBack: FullScreenAdsShowListener)
    fun destroyAd()
}