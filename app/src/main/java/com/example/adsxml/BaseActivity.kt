package com.example.adsxml

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.core.ad_units.core.AdUnit

abstract class BaseActivity : AppCompatActivity() {

    private val nativeAd: AdUnit? = null

    lateinit var mContext: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }
}