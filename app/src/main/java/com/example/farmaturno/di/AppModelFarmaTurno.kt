package com.example.farmaturno.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.farmaturno.data.FarmaRepositoryImpl
import com.example.farmaturno.data.local.database.AppDataBase
import com.example.farmaturno.data.local.database.dao.ActualizacionDao
import com.example.farmaturno.data.local.database.dao.FarmaciaDao
import com.example.farmaturno.data.network.retrofit.FarmaApiService
import com.example.farmaturno.di.constant.Config
import com.example.farmaturno.domain.Repository
import com.example.farmaturno.ui.home.HomePrincipalViewModel
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModelFarmaTurno {

    @Provides
    fun provideOkHtppClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Config.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideFarmaApiService(retrofit: Retrofit): FarmaApiService {
        return retrofit.create(FarmaApiService::class.java)
    }

    @Provides
    fun provideRepository(
        apiService: FarmaApiService,
        farmaciaDao: FarmaciaDao,
        actualizacionDao: ActualizacionDao
    ): Repository {
        return FarmaRepositoryImpl(apiService,farmaciaDao,actualizacionDao)
    }

    @Provides
    fun provideFarmaciaDao(appDataBase: AppDataBase):FarmaciaDao{
        return appDataBase.farmaciaDao()
    }

    @Provides
    fun provideActualizacionDao(appDataBase: AppDataBase):ActualizacionDao{
        return appDataBase.actualizacionDao()
    }

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase{
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "farmacias-db"
        ).addMigrations(AppDataBase.MIGRATION_1_2)
            .build()
    }
}