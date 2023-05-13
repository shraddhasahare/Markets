package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("defaultCity")
	val defaultCity: Boolean? = null,

	@field:SerializedName("cityName")
	val cityName: String? = null,

	@field:SerializedName("ipAddress")
	val ipAddress: String? = null,

	@field:SerializedName("cityId")
	val cityId: String? = null,

	@field:SerializedName("ipOriginCity")
	val ipOriginCity: String? = null
)
