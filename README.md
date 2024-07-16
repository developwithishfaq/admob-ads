## Setup
> Step 1: Add it to your build.gradle/setting gradle (project):
```gradle
allprojects {
    repositories {
        maven {
            url = URI("https://jitpack.io")
        }
    }
}
```
> Step 2: Add it to your build.gradle (app)

```gradle
dependencies {
     implementation("com.github.developwithishfaq:admob-ads:0.2")
}
```

# Ishfaq Ads Sdk
Using this library we can Easily Implement Admob Ads.

**App Id**
Add Your Admob Original App Id Like This Inside Manifest <application 
```
  <!-- Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713 -->
  <meta-data
    android:name="com.google.android.gms.ads.APPLICATION_ID"
    android:value="ca-app-pub-3940256099942544~3347511713"/>
```


**Sdk Intialization**
```gradle
  // I am using koin for di
  private val adsManager: IshfaqAdsSdk by inject()
  // Call this Function For Ad Network's Intializations
  adsManager.initAdsSdk(mContext) {
  }
```
## Native Ads Implementation
At First create an instance of NativeAdsManager.
```
  private val nativeAdsManager: NativeAdsManager by inject()
```
Now Create Controllers For Your Native Ads.
```
  nativeAdsManager.addNewController(
    adKey = "MainNative",
    adId = "ca-app-pub-3940256099942544/2247696110"
  )
```
Now Lets Show Native Ads
We Made Easy For You To Implement Native Ads On Your Screens, Just Extend Your Activity With Sdk's **IshfaqNativeAdsActivity**, and call this funtion.

```
  showNativeAd(
    key = "MainNative",
    layoutName = "layout_name.xml",
    enabled = true,
    adFrame = binding.adFrame,
    showShimmerLayout = true,
    oneTimeUse = true
)
```
Each paramteres is self descriptive.


