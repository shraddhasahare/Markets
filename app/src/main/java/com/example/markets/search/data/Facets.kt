package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Facets(
        @SerializedName("text")
        var text: Text? = null,
        @SerializedName("range")
        var range: Range? = null,
        @SerializedName("multilevel")
        var multilevel: Multilevel? = null
)