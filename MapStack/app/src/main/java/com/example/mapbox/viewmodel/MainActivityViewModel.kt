package com.example.mapbox.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapbox.data.model.CountryModel
import com.example.mapbox.data.model.RecordItem
import com.example.mapbox.data.model.ResponseModel
import com.example.mapbox.data.repository.CountryRepository
import com.example.mapbox.data.repository.MainRepository
import com.example.mapbox.view.main.view.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel() : ViewModel() {

    private val records: MutableLiveData<List<RecordItem>> by lazy {
        MutableLiveData<List<RecordItem>>().also { viewModelScope.launch { fetchCountries() } }
    }

    /*init {
        GlobalScope.launch { fetchCountries() }
    }*/

    private suspend fun fetchCountries() {
        val result = MainActivity.mainRepository?.getDataRecords()
        Log.d("TAG_DATA", "fetchCountries: "+result)
        if (result != null) {
            records.postValue(result.record as List<RecordItem>)
        }
    }

    fun getRecordList() : LiveData<List<RecordItem>>{
        return records
    }


}