package com.example.iamorganiser.util

import android.app.Application

class App : Application() {

    lateinit var prefManager: PrefManager
    var token: String = ""

    override fun onCreate() {
        super.onCreate()
        app = this
        prefManager = PrefManager.get(this)
        token = prefManager.accessToken
    }

    companion object {
        lateinit var app: App
    }
}