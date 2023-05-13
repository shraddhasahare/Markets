package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class FiltersOffer (

        @SerializedName("count")
        val count : Int?=null,

        @SerializedName("name")
        val name : String?=null,

        @SerializedName("id")
        val id : String?=null
)