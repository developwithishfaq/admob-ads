package com.example.adsxml.base

import android.app.Activity
import android.os.Bundle
import com.example.banner_ads.BannerAdsManager
import com.example.banner_ads.base.BaseBannerAdsActivity
import com.example.inter.InterstitialAdsManager
import org.koin.android.ext.android.inject

abstract class BaseActivity : BaseBannerAdsActivity() {
    lateinit var mContext: Activity

    val interAdsManager: InterstitialAdsManager by inject()
    val bannerAdsManager: BannerAdsManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }
}