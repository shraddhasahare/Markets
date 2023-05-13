package com.example.markets.search.data

import com.google.gson.annotations.SerializedName


data class CityIdResponse(

	@field:SerializedName("data")
		val data: Data? = null,

	@field:SerializedName("status")
		val status: String? = null,

	@field:SerializedName("statusCode")
		val statusCode: Int? = null
)