package com.example.markets.search.data

import `in`.bajajfinservmarkets.app.search.data.EsSearchResponse
import com.google.gson.annotations.SerializedName

data class SearchEsSearch(
        @SerializedName("searchMetaData")
        val searchMetaData : SearchMetaData? = null,
        @SerializedName("response")
        val response : EsSearchResponse? = null
)