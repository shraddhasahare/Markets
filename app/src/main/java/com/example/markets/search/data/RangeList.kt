package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class RangeList(
        @SerializedName("facetName")
        var facetName: String? = null,
        @SerializedName("values")
        var values:Values ? = null,
        @SerializedName("displayName")
        var displayName:String ?=null,
        @SerializedName("position")
        var position:Int?= null
)