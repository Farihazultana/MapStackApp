package com.example.mapbox.data.api


import com.example.mapbox.data.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET(".")
    suspend fun getRecords() : Response<ResponseModel>
}