package com.example.adsxml

import android.os.Bundle
import com.example.adsxml.ads.showInterAd
import com.example.adsxml.base.BaseActivity
import com.example.adsxml.databinding.ActivityMainBinding
import com.example.banner_ads.BannerAdSizes
import com.example.core.IshfaqAdsSdk
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adsManager: IshfaqAdsSdk by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adsManager.initAdsSdk(mContext) {

        }

        interAdsManager.addNewController(
            adKey = "MainInter",
            adId = "ca-app-pub-3940256099942544/1033173712"
        )
        bannerAdsManager.addNewController(
            adKey = "MainBanner",
            adId = "ca-app-pub-3940256099942544/9214589741"
        )

        binding.preloadAd.setOnClickListener {
            interAdsManager.getAdController("MainInter")?.loadAd(mContext, null)
        }
        binding.showAd.setOnClickListener {
            showInterAd(
                enable = true,
                context = mContext,
                key = "MainInter",
                interAdsManager = interAdsManager
            ) { adShown ->

            }
        }

        showBannerAd(
            "MainBanner",
            BannerAdSizes.MediumRectangle,
            enabled = true,
            adFrame = binding.adFrame,
            showShimmerLayout = true
        )
    }
}