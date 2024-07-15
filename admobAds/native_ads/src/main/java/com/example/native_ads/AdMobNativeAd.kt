package com.example.native_ads

import com.example.core.ad_units.IshfaqNativeAd
import com.example.core.ad_units.core.AdType
import com.google.android.gms.ads.nativead.NativeAd

class AdMobNativeAd(val nativeAd: NativeAd) : IshfaqNativeAd {
    override fun getTitle(): String? {
        return nativeAd.headline
    }

    override fun getDescription(): String? {
        return nativeAd.body
    }

    override fun getCtaText(): String? {
        return nativeAd.callToAction
    }

    override fun getAdvertiserName(): String? {
        return nativeAd.advertiser
    }

    override fun destroyAd() {

    }


    override fun getAdType(): AdType {
        return AdType.NATIVE
    }
}