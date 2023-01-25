package com.example.mapbox.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Countries_table")
data class CountryModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "location_name")
    var location: String,
    @ColumnInfo(name = "lat")
    var lat: Double,
    @ColumnInfo(name = "long")
    var long: Double?
)

