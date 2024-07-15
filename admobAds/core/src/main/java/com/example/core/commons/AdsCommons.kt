package com.example.core.commons

import android.util.Log

object AdsCommons {
    fun logAds(message: String, isError: Boolean = false) {
        if (isError) {
            Log.e("adsPlugin", "Ads: $message")
        } else {
            Log.d("adsPlugin", "Ads: $message")
        }
    }
}