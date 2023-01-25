package com.example.mapbox.data.repository


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mapbox.data.api.API
import com.example.mapbox.data.api.RetrofitHelper
import com.example.mapbox.data.model.CountryModel
import com.example.mapbox.data.model.ResponseModel
import com.example.mapbox.view.main.view.MainActivity



class MainRepository(private val context: Context, private val apiHelper: RetrofitHelper) {


    private suspend fun getDataRecords(): ResponseModel? {
        val result = apiHelper.getInstance().create(API::class.java)
        return result.getRecords().body()
    }

    private val countryDAO = MainActivity.countriesDataBase?.countryDAO()


   suspend fun insertCountries(){
       if(isOnline(context)) {
           getDataRecords()
               ?.record?.forEach {
                   countryDAO?.insertLocation(CountryModel(it?.id,
                       it?.locName,
                       it?.lat,
                       it?.jsonMemberLong))
               }

           //countryModel?.let { MainActivity.countriesDataBase?.countryDAO()?.insertLocation(it) }
       }
    }
    fun getCountries(): LiveData<List<CountryModel>>?{
        return countryDAO?.getAllCountries()
    }


    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}