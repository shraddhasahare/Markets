package com.example.markets.search.data

import com.example.markets.search.data.SearchMetaData
import com.google.gson.annotations.SerializedName

class SearchEmiStore(
    @SerializedName("searchMetaData")
                val searchMetaData : SearchMetaData? = null,
    @SerializedName("response")
                val suggestionResponse : SuggestionResponse? = null
)