package com.example.hca_stackexchange.client

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val BASE_URL:String = "https://api.stackexchange.com/"

    //create okhttp client
    val okHttp = OkHttpClient.Builder()

        //create retrofit builder
    var retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    //create retrofit instance
    private val retrofit = retrofitBuilder.build()

    fun <T> buildService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }
}