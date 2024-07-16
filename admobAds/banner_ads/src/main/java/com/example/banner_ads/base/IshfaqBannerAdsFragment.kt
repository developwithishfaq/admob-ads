package com.example.banner_ads.base

import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.banner_ads.AdmobBannerAd
import com.example.banner_ads.AdmobBannerAdsController
import com.example.banner_ads.BannerAdSizes
import com.example.banner_ads.IshfaqBannerAdsManager
import com.example.banner_ads.R
import com.example.core.AdsController
import com.example.core.AdsLoadingStatusListener
import com.example.core.ad_units.core.AdUnit
import com.example.core.commons.NativeConstants.inflateLayoutByName
import com.example.core.commons.NativeConstants.makeGone
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject

class IshfaqBannerAdsFragment : Fragment() {

    private val ishfaqBannerAdsManager: IshfaqBannerAdsManager by inject()
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
        bannerAdController = ishfaqBannerAdsManager.getAdController(key)
        (bannerAdController as? AdmobBannerAdsController)?.setAdSize(adSize)
        bannerAdController?.loadAd(
            (requireActivity()), object : AdsLoadingStatusListener {
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
            requireActivity(),
            adFrame!!
        )
    }

    private fun showShimmerLayout() {
        val shimmerLayout =
            LayoutInflater.from(requireActivity()).inflate(R.layout.shimmer, null, false)
        val adLayout = adSize.layoutName.inflateLayoutByName(requireActivity())
        shimmerLayout?.findViewById<ShimmerFrameLayout>(R.id.shimmerRoot)?.let { shimmer ->
            shimmer.removeAllViews()
            shimmer.addView(adLayout)
            adFrame?.removeAllViews()
            adFrame?.addView(shimmer)
        }
    }

    override fun onPause() {
        super.onPause()
        bannerAdController?.resetListener(requireActivity())
    }

    override fun onDestroy() {
        super.onDestroy()
        bannerAdController?.destroyAd(requireActivity())
    }
}