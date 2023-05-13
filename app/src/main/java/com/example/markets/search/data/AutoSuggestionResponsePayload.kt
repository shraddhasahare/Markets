package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class AutoSuggestionResponsePayload(
        @SerializedName("emi-store")
        val emiStore : SearchEmiStore? = null,
        @SerializedName("es-search")
        val esSearch : SearchEsSearch? = null
)