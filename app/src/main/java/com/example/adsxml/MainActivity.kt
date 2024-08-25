package com.example.adsxml

import android.os.Bundle
import com.example.adsxml.databinding.ActivityMainBinding
import com.example.banner_ads.IshfaqBannerAdsManager
import com.example.core.IshfaqAdsSdk
import com.example.core.ad_units.IshfaqInterstitialAd
import com.example.core.commons.AdsCommons.logAds
import com.example.core.commons.IshfaqConfigs
import com.example.core.commons.NativeTemplates
import com.example.inter.IshfaqInterstitialAdsManager
import com.example.native_ads.IshfaqNativeAdsManager
import com.example.native_ads.base.IshfaqNativeAdsActivity
import org.koin.android.ext.android.inject

class MainActivity : IshfaqNativeAdsActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adsManager: IshfaqAdsSdk by inject()

    private val nativeAdsManager: IshfaqNativeAdsManager by inject()
    private val bannerAdsManager: IshfaqBannerAdsManager by inject()
    private val interAdsManager: IshfaqInterstitialAdsManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adsManager.initAdsSdk(this@MainActivity) {

        }
        interAdsManager.addNewController(
            adKey = "MainInter",
            adId = IshfaqConfigs.TestInterId
        )
        val controller = interAdsManager.getAdController("MainInter")
        val interAd = controller?.getAvailableAd() as? IshfaqInterstitialAd

        binding.showAd.setOnClickListener {
            interAdsManager.tryShowingInterstitialAd(
                enable = true,
                key = "MainInter",
                context = this@MainActivity,
                requestNewIfNotAvailable = true,
                requestNewIfAdShown = true,
                onAdDismiss = { adShown: Boolean ->
                    logAds("Ad Shown=$adShown")
                }
            )
        }

        showNativeAd(
            key = "MainNative",
            layoutName = NativeTemplates.TemplateOne,
            enabled = true,
            adFrame = binding.adFrame,
            showShimmerLayout = true,
            oneTimeUse = true,
            nativeAdsManager = nativeAdsManager
        )

        /*
        nativeAdsManager.addNewController(
            adKey = "MainNative",
            adId = IshfaqConfigs.TestBannerId
        )

        showBannerAd(
            key = "MainBanner",
            bannerType = BannerAdSizes.MediumRectangle,
            enabled = true,
            adFrame = binding.adFrame,
            showShimmerLayout = true,
            bannersManager = bannerAdsManager
        )*/
    }
}