package com.example.core

interface AdsManager {
    fun getAdController(key: String): AdsController?
    fun addNewController(adKey: String, adId: String)
}