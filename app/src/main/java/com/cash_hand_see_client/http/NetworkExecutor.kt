package com.cash_hand_see_client.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetworkExecutor private constructor() {


    companion object {
        @Volatile
        private var mInstance: NetworkExecutor? = null
        val instance: NetworkExecutor?
            get() {
                if (mInstance == null) {
                    synchronized(NetworkExecutor::class.java) {
                        if (mInstance == null) {
                            mInstance = NetworkExecutor()
                        }
                    }
                }
                return mInstance
            }
    }


    fun okHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.e("http-log", it)
        })

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        var builder: OkHttpClient.Builder = OkHttpClient.Builder()
        val headerInterceptor = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalRequest = chain.request()
                val requestBuilder: Request.Builder
                requestBuilder = originalRequest.newBuilder()
                    .header("token", "")
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        }
        builder
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
        return builder.build()
    }

    fun retrofit(url: String) {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient())
            .build()
    }


}