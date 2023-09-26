package com.example.farmaturno.di

import android.app.Application
import com.example.farmaturno.data.retrofit.FarmaApiService
import com.example.farmaturno.di.constant.Config
import com.example.farmaturno.ui.home.HomePrincipalViewModel
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModelFarmaTurno {

    @Provides
    fun provideOkHtppClient():OkHttpClient{
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFarmaApiService(retrofit: Retrofit): FarmaApiService{
        return retrofit.create(FarmaApiService::class.java)
    }
}