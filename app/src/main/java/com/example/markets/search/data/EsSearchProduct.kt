package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class EsSearchProduct(
        @SerializedName("feature")
        val feature : List<Feature>? = null,
        @SerializedName("content")
        val content : List<Content>? = null,
        @SerializedName("product")
        val product : List<Product>? = null
)