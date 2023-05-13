package com.example.markets.search.data

import com.example.markets.search.data.SearchProductsPayload
import com.google.gson.annotations.SerializedName

data class SearchProductsResponse(
        @SerializedName("payload") val payload : SearchProductsPayload? = null,
        @SerializedName("status") val status : String?=null,
        @SerializedName("errorBean") val errorBean : String?=null

)