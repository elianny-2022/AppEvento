package com.edu.ucne.appevento.di

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.room.Room
import com.edu.ucne.appevento.AppEvento
import com.edu.ucne.appevento.data.remote.EventoApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val baseUrl = "http://192.168.1.3:8080"
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesApi(): EventoApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.3:8080")
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .build()
            .create(EventoApi::class.java)
    }

}