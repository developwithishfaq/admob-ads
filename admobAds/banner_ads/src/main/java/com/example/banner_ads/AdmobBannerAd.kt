package com.example.banner_ads

import android.app.Activity
import android.widget.FrameLayout
import com.example.core.FullScreenAdsShowListener
import com.example.core.ad_units.IshfaqBannerAd
import com.example.core.ad_units.core.AdType
import com.google.android.gms.ads.AdView

class AdmobBannerAd(
    private val adView: AdView
) : IshfaqBannerAd {
    override fun showInter(context: Activity, callBack: FullScreenAdsShowListener) {

    }

    override fun destroyAd() {
        adView.destroy()
    }

    override fun populateAd(context: Activity, view: FrameLayout) {
        view.removeAllViews()
        view.addView(adView)
    }

    override fun getAdType(): AdType {
        return AdType.BANNER
    }
}