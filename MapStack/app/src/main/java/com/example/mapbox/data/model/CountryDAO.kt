package com.example.mapbox.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CountryDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLocation(countryModel: CountryModel)

}