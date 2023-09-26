package com.example.farmaturno

import android.app.Application
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FarmaTurnoApp: Application(){
    override fun onCreate() {
        super.onCreate()
        Places.initialize(applicationContext, "AIzaSyDV-1kOWik8IwuvBG8vMstuLhHNnpcRLrs")
    }
}