package com.example.core

interface FullScreenAdsShowListener {
    fun onAdDismiss() {}
    fun onAdShown() {}
    fun onAdShownFailed() {}
    fun onAdClick() {}
}