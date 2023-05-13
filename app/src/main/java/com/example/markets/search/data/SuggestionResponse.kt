package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class SuggestionResponse(
        @SerializedName("start")
        val start : Int? = null,
        @SerializedName("numberOfProducts")
        val numberOfProducts : Int? = null,
        @SerializedName("products")
        val products : List<SearchEmiStoreProduct>? = null
)