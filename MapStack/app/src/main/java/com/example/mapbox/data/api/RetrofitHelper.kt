package com.example.mapbox.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    var baseUrl = "https://api.jsonbin.io/v3/b/5fe1a71387e11161f87da311/"


    fun getInstance(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor {  interceptor ->
            val requestBuilder = interceptor.request().newBuilder()
            requestBuilder.addHeader(
                "X-Master-Key",
                "\$2b\$10\$b.6ND/37NEWc87cZFKboOun3kgphgcqyojnouddamSDSwm.sE76fO")
            return@Interceptor interceptor.proceed(requestBuilder.build())
        })
        val client:OkHttpClient = httpClient.build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}