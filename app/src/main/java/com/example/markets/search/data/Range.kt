package com.example.markets.search.data
import com.google.gson.annotations.SerializedName

data class Range(
        @SerializedName("list")
        var list:List<RangeList>?= null
)