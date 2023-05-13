package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

class FiltersPurpose(
        @SerializedName("value")
        val value : String?=null,

        @SerializedName("id")
        val id : String?=null
)