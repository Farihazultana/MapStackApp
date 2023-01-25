package com.example.mapbox.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapbox.data.model.CountryModel
import com.example.mapbox.data.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class countryViewModel(private val countryRepository: CountryRepository) : ViewModel(){
    fun addCountries(country : CountryModel){
        viewModelScope.launch (Dispatchers.IO){
            countryRepository.insertCountries(country)
        }
    }
}