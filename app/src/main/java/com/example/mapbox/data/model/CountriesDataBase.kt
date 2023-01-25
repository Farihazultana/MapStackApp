package com.example.mapbox.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CountryModel::class],
    version = 1
)

abstract class CountriesDataBase : RoomDatabase() {
    abstract fun countryDAO(): CountryDAO

    companion object {
        @Volatile
        private var INSTANCE: CountriesDataBase? = null

        fun getDatabase(context: Context): CountriesDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountriesDataBase::class.java,
                    "country_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}