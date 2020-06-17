package com.example.myproject.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication() = application

    @Singleton
    @Provides
    fun provideSharedPreferences() = application.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)!!

    @Singleton
    @Provides
    fun provideGson(): Gson{
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {

        val clientBuilder = OkHttpClient.Builder()

        // start debug scope
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        // end

        clientBuilder.addInterceptor(httpLoggingInterceptor)
            .readTimeout(10,TimeUnit.SECONDS)
            .connectTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)

        return clientBuilder.build()
    }


}