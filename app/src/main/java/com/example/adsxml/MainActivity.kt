package com.example.adsxml

import android.os.Bundle
import com.example.adsxml.databinding.ActivityMainBinding
import com.example.banner_ads.IshfaqBannerAdsManager
import com.example.core.FullScreenAdsShowListener
import com.example.core.IshfaqAdsSdk
import com.example.core.ad_units.IshfaqInterstitialAd
import com.example.core.commons.IshfaqConfigs
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
        interAd?.showInter(
            context = this@MainActivity,
            callBack = object : FullScreenAdsShowListener {
                override fun onAdShownFailed() {
                    super.onAdShownFailed()
                }

                override fun onAdDismiss() {
                    super.onAdDismiss()
                }

                override fun onAdShown() {
                    super.onAdShown()
                }

                override fun onAdClick() {
                    super.onAdClick()
                }
            }
        )

        /*controller?.loadAd(context = this@MainActivity, callback = null)
        */

        binding.showAd.setOnClickListener {
            interAdsManager.tryShowingInterstitialAd(
                enable = true,
                key = "MainInter",
                context = this@MainActivity,
                requestNewIfNotAvailable = true,
                requestNewIfAdShown = true,
                onAdDismiss = { adShown: Boolean ->

                }
            )
        }

        /*
        nativeAdsManager.addNewController(
            adKey = "MainNative",
            adId = IshfaqConfigs.TestBannerId
        )

        showNativeAd(
            key = "MainNative",
            layoutName = NativeTemplates.TemplateTwo,
            enabled = true,
            adFrame = binding.adFrame,
            showShimmerLayout = true,
            oneTimeUse = true,
            nativeAdsManager = nativeAdsManager
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