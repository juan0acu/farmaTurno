package com.example.farmaturno.di

import com.example.farmaturno.data.retrofit.FarmaApiService
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
            .baseUrl("https://midas.minsal.cl/farmacia_v2/WS")
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