package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class TextList(
        @SerializedName("facetName")
        var facetName: String? = null,
        @SerializedName("filterField")
        var filterField: String? = null,
        @SerializedName("values")
        var values: List<String>? = null,
        @SerializedName("displayName")
        var displayName: String? = null,
        @SerializedName("position")
        var position: Integer? = null
)