package com.example.adsxml.di

import com.example.app_open.IshfaqAppOpenAdsManager
import com.example.banner_ads.IshfaqBannerAdsManager
import com.example.core.IshfaqAdsSdk
import com.example.inter.IshfaqInterstitialAdsManager
import com.example.native_ads.IshfaqNativeAdsManager
import org.koin.dsl.module

val SharedModule = module {
    single {
        IshfaqAppOpenAdsManager()
    }
    single {
        IshfaqNativeAdsManager()
    }
    single {
        IshfaqAdsSdk()
    }
    single {
        IshfaqInterstitialAdsManager()
    }
    single {
        IshfaqBannerAdsManager()
    }
    single {
        IshfaqAdsSdk()
    }
}