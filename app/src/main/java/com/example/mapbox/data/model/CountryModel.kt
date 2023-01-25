package com.example.mapbox.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Countries_table")
data class CountryModel(
    @PrimaryKey
    var id: Int?,
    @ColumnInfo(name = "location_name")
    var location: String?,
    @ColumnInfo(name = "lat")
    var lat: Double?,
    @ColumnInfo(name = "longitude")
    var longitude: Double?
)

