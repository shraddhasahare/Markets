package com.example.markets.search.data

import com.google.gson.annotations.SerializedName
data class Text(
        @SerializedName("list")
        var textList: List<TextList>? = null
)