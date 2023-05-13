package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class FiltersCategory (

        @SerializedName("value")
        val value : String?=null,

        @SerializedName("id")
        val id : String?=null
)