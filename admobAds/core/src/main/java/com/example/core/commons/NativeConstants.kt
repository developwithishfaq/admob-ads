package com.example.core.commons

import android.content.Context
import android.view.LayoutInflater
import android.view.View

object NativeConstants {
    fun Context.getLayoutId(layoutName: String): Int {
        return resources.getIdentifier(layoutName, "layout", packageName)
    }

    fun String.inflateLayoutByName(context: Context): View? {
        val layoutId = context.getLayoutId(this)
        return if (layoutId != 0) {
            LayoutInflater.from(context).inflate(layoutId, null)
        } else {
            null
        }
    }

    fun View?.makeGone(value: Boolean  =true) {
        this?.visibility = if (value) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    fun View.makeVisible(value: Boolean=true) {
        visibility = if (value) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}