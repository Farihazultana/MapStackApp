package com.example.mapbox.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CountryDAO {

    @Query("SELECT * FROM Countries_table")
    fun getAllCountries(): LiveData<List<CountryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(countryModel: CountryModel)

}