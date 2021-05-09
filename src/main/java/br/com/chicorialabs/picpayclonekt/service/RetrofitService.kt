package br.com.chicorialabs.picpayclonekt.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val URL = "http://192.168.0.187:8080/"

object RetrofitService {

    val instance = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    inline fun <reified T> criarService() = instance.create(T::class.java)
}