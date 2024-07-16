# Ishfaq Ads Sdk
Using this library we can Easily Implement Admob Ads.
## Sdk Intialization
```gradle
  // I am using koin for di
  private val adsManager: IshfaqAdsSdk by inject()
  // Call this Function For Ad Network's Intializations
  adsManager.initAdsSdk(mContext) {
  }
```
