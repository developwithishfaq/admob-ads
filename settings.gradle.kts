import java.net.URI

include(":admobAds:app_open")


include(":admobAds:banner_ads")


pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "AdsXml"
include(":app")
include(":admobAds")
include(":admobAds:core")
include(":admobAds:inter")
include(":admobAds:native_ads")
