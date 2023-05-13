package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class EssearchProducts (
    @SerializedName("_id") val _id : String?=null,
    @SerializedName("_score") val _score : Int?=null,
    @SerializedName("_source") val _source : Source?=null
)