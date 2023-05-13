package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Response (

        @SerializedName("from") val from : Int? = null,
        @SerializedName("totalrecords") val totalrecords : Int? = null,
        @SerializedName("suggested_search_keyword") val suggested_search_suggested_search_keywordword : String? = null
)