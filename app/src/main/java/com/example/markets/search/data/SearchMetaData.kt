package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class SearchMetaData(
        @SerializedName("status") val status : Int? = null,
        @SerializedName("queryTime") val queryTime : Int? = null,
        @SerializedName("queryParams") val queryParams : QueryParams ? = null)