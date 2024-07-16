package com.example.native_ads.base

import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.core.AdsController
import com.example.core.AdsLoadingStatusListener
import com.example.core.ad_units.core.AdUnit
import com.example.core.commons.AdsCommons.logAds
import com.example.core.commons.NativeConstants.inflateLayoutByName
import com.example.native_ads.IshfaqNativeAdsManager
import com.example.native_ads.R
import com.example.native_ads.ui.IshfaqNativeView
import com.facebook.shimmer.ShimmerFrameLayout

class IshfaqNativeAdsFragment : Fragment() {

    private var nativeAdsManager: IshfaqNativeAdsManager? = null
    private var nativeAdController: AdsController? = null
    private var nativeAd: AdUnit? = null

    private var isShowAdCalled = false
    private var key = ""
    private var layoutName = ""
    private var enabled = false
    private var adFrame: FrameLayout? = null
    private var adLoaded = false
    private var oneTimeUse = false
    private var showShimmerLayout = false


    fun showNativeAd(
        key: String,
        layoutName: String = "native_ad_normal_layout",
        enabled: Boolean,
        adFrame: FrameLayout,
        nativeAdsManager: IshfaqNativeAdsManager,
        showShimmerLayout: Boolean = true,
        oneTimeUse: Boolean = true
    ) {
        this.key = key
        this.oneTimeUse = oneTimeUse
        this.layoutName = layoutName
        this.enabled = enabled
        this.showShimmerLayout = showShimmerLayout
        this.nativeAdsManager = nativeAdsManager
        this.adLoaded = false
        this.adFrame = adFrame
        isShowAdCalled = true
        loadAd()
    }

    override fun onResume() {
        super.onResume()
        if (isShowAdCalled && enabled) {
            loadAd()
        }
    }

    private fun loadAd() {
        if (adLoaded && nativeAd != null) {
            populateNativeAd()
            return
        }
        if (enabled.not()) {
            return
        }
        if (showShimmerLayout) {
            showShimmerLayout()
        }
        nativeAdController = nativeAdsManager?.getAdController(key)
        nativeAdController?.loadAd(
            (requireActivity()), object : AdsLoadingStatusListener {
                override fun onAdLoaded() {
                    if (adLoaded) {
                        return
                    }
                    adLoaded = true
                    nativeAd = nativeAdController?.getAvailableAd()
                    logAds(
                        "Fragment=On Ad Loaded,Is Ad Ok=${nativeAd != null}",
                        isError = nativeAd == null
                    )
                    populateNativeAd()
                }

                override fun onAdFailedToLoad(message: String, code: Int) {

                }
            }
        )
    }

    private fun showShimmerLayout() {
        val shimmerLayout =
            LayoutInflater.from(requireActivity()).inflate(R.layout.shimmer, null, false)

        val adLayout = layoutName.inflateLayoutByName(requireActivity())

        shimmerLayout?.findViewById<ShimmerFrameLayout>(R.id.shimmerRoot)?.let { shimmer ->
            shimmer.removeAllViews()
            shimmer.addView(adLayout)
            adFrame?.removeAllViews()
            adFrame?.addView(shimmer)
        }

    }


    override fun onPause() {
        super.onPause()
        nativeAdController?.resetListener(requireActivity())
    }


    private fun populateNativeAd() {
        logAds("populateNativeAd Called")
        nativeAd?.let {
            layoutName.inflateLayoutByName(requireActivity())?.let { layout ->
                adFrame?.removeAllViews()
                adFrame?.addView(layout)
                layout.findViewById<IshfaqNativeView>(R.id.ishfaqNative)?.let { view ->
                    (nativeAdsManager as? IshfaqNativeAdsManager)?.populateAd(view, it) {
                        if (oneTimeUse) {
                            destroyLoadedAd()
                        }
                    }
                }
            }
        }
    }

    private fun destroyLoadedAd() {
        nativeAdController?.destroyAd(requireActivity())
    }

}