package com.example.mapbox.data.repository

import com.example.mapbox.data.model.CountryDAO
import com.example.mapbox.data.model.CountryModel

class CountryRepository(private val countryDAO: CountryDAO) {
    suspend fun insertCountries(countryModel: CountryModel) =
        countryDAO.insertLocation(countryModel)
}