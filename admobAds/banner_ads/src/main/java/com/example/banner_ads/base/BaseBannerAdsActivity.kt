package com.example.banner_ads.base

import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.banner_ads.AdmobBannerAd
import com.example.banner_ads.AdmobBannerAdsController
import com.example.banner_ads.BannerAdSizes
import com.example.banner_ads.BannerAdsManager
import com.example.banner_ads.R
import com.example.core.AdsController
import com.example.core.AdsLoadingStatusListener
import com.example.core.ad_units.core.AdUnit
import com.example.core.commons.NativeConstants.inflateLayoutByName
import com.example.core.commons.NativeConstants.makeGone
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject

abstract class BaseBannerAdsActivity : AppCompatActivity() {
    private val bannerAdsManager: BannerAdsManager by inject()
    private var bannerAdController: AdsController? = null
    private var bannerAd: AdUnit? = null

    private var isShowAdCalled = false
    private var key = ""
    private var adSize: BannerAdSizes = BannerAdSizes.Banner
    private var enabled = false
    private var adFrame: FrameLayout? = null
    private var adLoaded = false
    private var oneTimeUse = false
    private var showShimmerLayout = false

    override fun onResume() {
        super.onResume()
        if (isShowAdCalled && enabled) {
            loadAd()
        }
    }

    fun showBannerAd(
        key: String,
        layoutName: BannerAdSizes = BannerAdSizes.Banner,
        enabled: Boolean,
        adFrame: FrameLayout,
        showShimmerLayout: Boolean = true,
        oneTimeUse: Boolean = true
    ) {
        this.key = key
        this.enabled = enabled
        this.oneTimeUse = oneTimeUse
        this.adSize = layoutName
        this.showShimmerLayout = showShimmerLayout
        this.adLoaded = false
        this.adFrame = adFrame
        isShowAdCalled = true
        loadAd()
    }

    private fun loadAd() {
        if (adFrame == null) {
            return
        }
        if (adLoaded && bannerAd != null) {
            populateAd()
            return
        }
        if (enabled.not()) {
            return
        }
        if (showShimmerLayout) {
            showShimmerLayout()
        }
        bannerAdController = bannerAdsManager.getAdController(key)
        (bannerAdController as? AdmobBannerAdsController)?.setAdSize(adSize)
        bannerAdController?.loadAd(
            (this@BaseBannerAdsActivity), object : AdsLoadingStatusListener {
                override fun onAdLoaded() {
                    adLoaded = true
                    bannerAd = bannerAdController?.getAvailableAd()
                    if (adFrame != null) {
                        populateAd()
                    }
                }

                override fun onAdFailedToLoad(message: String, code: Int) {
                    adFrame?.makeGone()
                }
            }
        )
    }

    fun populateAd() {
        (bannerAd as? AdmobBannerAd)?.populateAd(
            this@BaseBannerAdsActivity,
            adFrame!!
        )
    }

    private fun showShimmerLayout() {
        val shimmerLayout =
            LayoutInflater.from(this@BaseBannerAdsActivity).inflate(R.layout.shimmer, null, false)
        val adLayout = adSize.layoutName.inflateLayoutByName(this@BaseBannerAdsActivity)
        shimmerLayout?.findViewById<ShimmerFrameLayout>(R.id.shimmerRoot)?.let { shimmer ->
            shimmer.removeAllViews()
            shimmer.addView(adLayout)
            adFrame?.removeAllViews()
            adFrame?.addView(shimmer)
        }
    }

    override fun onPause() {
        super.onPause()
        bannerAdController?.resetListener(this@BaseBannerAdsActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        bannerAdController?.destroyAd(this@BaseBannerAdsActivity)
    }

}