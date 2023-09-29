package com.enigma.yokiuserfinderv2.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Client {

    private const val BASE_URL = "https://api.github.com/"

//    Deklarasi URL tujuan
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//    Instance
    val apiInstance: Api = retrofit.create(Api::class.java)

}