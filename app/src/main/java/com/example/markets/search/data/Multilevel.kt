package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Multilevel(
        @SerializedName("bucket")
        var bucket: List<Bucket>? = null
)