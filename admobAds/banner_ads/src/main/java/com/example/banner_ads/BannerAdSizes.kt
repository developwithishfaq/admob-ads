package com.example.banner_ads

enum class BannerAdSizes(val layoutName: String) {
    Banner("banner_shimmer"),
    FullBanner("banner_shimmer"),
    LargeBanner("banner_shimmer"),
    MediumRectangle("banner_shimmer"),
    CollapsibleTop("banner_shimmer"),
    CollapsibleBottom("banner_shimmer"),
}

fun BannerAdSizes.isCollapsible(): Boolean {
    return (this == BannerAdSizes.CollapsibleTop) || (this == BannerAdSizes.CollapsibleBottom)
}