package com.example.core.ad_units

import android.app.Activity
import android.view.View
import android.widget.FrameLayout
import com.example.core.FullScreenAdsShowListener
import com.example.core.ad_units.core.AdUnit

interface IshfaqBannerAd : AdUnit {
    fun showInter(context: Activity, callBack: FullScreenAdsShowListener)
    fun destroyAd()
    fun populateAd(context: Activity,view: FrameLayout)
}