package com.example.shadidemo.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val BASE_URL = "https://randomuser.me/api/"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {

            val logLevel = HttpLoggingInterceptor.Level.BODY
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = logLevel

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit
    }
}