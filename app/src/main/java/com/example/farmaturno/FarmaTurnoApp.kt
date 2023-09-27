package com.example.farmaturno

import android.app.Application
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FarmaTurnoApp: Application(){
    override fun onCreate() {
        super.onCreate()
        val apikey = BuildConfig.MAPS_API_KEY
        Places.initialize(applicationContext, apikey)
    }
}