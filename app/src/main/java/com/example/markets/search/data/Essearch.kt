package com.example.markets.search.data

import com.google.gson.annotations.SerializedName


data class Essearch (

        @SerializedName("response") val response : Response?= null,
        @SerializedName("products") val products : MutableList<EssearchProducts>?= null
)