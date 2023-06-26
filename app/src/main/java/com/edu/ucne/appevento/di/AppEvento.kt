package com.edu.ucne.appevento.di

import com.edu.ucne.appevento.AppEvento
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun providesUniqueApi(moshi: Moshi): AppEvento {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(AppEvento::class.java)
    }

}