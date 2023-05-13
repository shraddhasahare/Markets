package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class SearchProductsPayload(
        @SerializedName("status") val status : Boolean? = null,
        @SerializedName("message") val message : Int? = null,
        @SerializedName("data") val data : Int? = null,
        @SerializedName("url") val url : String? = null,
        @SerializedName("productType") val productType : ProductType? = null
)