package com.example.adsxml.base

import android.app.Activity
import android.os.Bundle
import com.example.banner_ads.IshfaqBannerAdsManager
import com.example.banner_ads.base.IshfaqBannerAdsActivity
import com.example.inter.IshfaqInterstitialAdsManager
import org.koin.android.ext.android.inject

abstract class IshfaqActivity : IshfaqBannerAdsActivity() {
    lateinit var mContext: Activity

    val interAdsManager: IshfaqInterstitialAdsManager by inject()
    val ishfaqBannerAdsManager: IshfaqBannerAdsManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }
}