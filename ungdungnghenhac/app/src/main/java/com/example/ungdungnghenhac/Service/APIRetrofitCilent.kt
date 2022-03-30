package com.example.ungdungnghenhac.Service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object APIRetrofitCilent {
    private var retrofit: Retrofit? = null

    fun getClient(base_url: String?): Retrofit? {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .writeTimeout(10000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .protocols(Arrays.asList(Protocol.HTTP_1_1))
            .build()
        val gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }
}