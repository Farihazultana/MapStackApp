package com.example.mapbox.data.model

import com.google.gson.annotations.SerializedName


data class ResponseModel(

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("record")
	val record: List<RecordItem?>? = null
)

data class RecordItem(

	@field:SerializedName("loc_name")
	val locName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("lat")
	val lat: Double? = null,

	@field:SerializedName("long")
	val jsonMemberLong: Double? = null
)

data class Metadata(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("private")
	val jsonMemberPrivate: Boolean? = null,

	@field:SerializedName("id")
	val id: String? = null
)
