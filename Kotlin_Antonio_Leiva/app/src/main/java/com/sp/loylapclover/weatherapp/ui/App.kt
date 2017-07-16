package com.sp.loylapclover.weatherapp.ui

import android.app.Application

/**
 * Created by Daniel on 16/07/2017.
 */
class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}