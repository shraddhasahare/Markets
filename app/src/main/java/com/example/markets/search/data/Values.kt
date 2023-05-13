package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Values(
        @SerializedName("counts")
        var counts:List<String>? = null,
        @SerializedName("gap")
        var gap:Int?=null,
        @SerializedName("start")
        var start: Int? = null,
        @SerializedName("end")
        var end: Int?=null
)