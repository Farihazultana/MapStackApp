package com.example.mapbox.viewmodel

import androidx.lifecycle.*
import com.example.mapbox.data.model.CountryModel
import com.example.mapbox.view.main.view.MainActivity.Companion.mainRepository
import kotlinx.coroutines.launch


class MainActivityViewModel() : ViewModel() {

    init {
        viewModelScope.launch {
            mainRepository?.insertCountries()
        }
    }

    fun getRecordList(): LiveData<List<CountryModel>>?{
        return mainRepository?.getCountries()
    }

}
