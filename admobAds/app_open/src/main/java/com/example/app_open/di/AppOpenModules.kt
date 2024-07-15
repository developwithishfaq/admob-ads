package com.example.app_open.di

import com.example.app_open.AppOpenAdsManager
import org.koin.dsl.module

val AppOpenModules = module {
    single {
        AppOpenAdsManager()
    }
}