package com.example.mapbox.data.repository

import com.example.mapbox.data.api.API
import com.example.mapbox.data.api.RetrofitHelper
import com.example.mapbox.data.model.CountryDAO
import com.example.mapbox.data.model.CountryModel
import com.example.mapbox.data.model.ResponseModel

class MainRepository(private val apiHelper: RetrofitHelper) {
    suspend fun getDataRecords(): ResponseModel? {
        val result = apiHelper.getInstance().create(API::class.java)
        return result.getRecords().body()
    }

}