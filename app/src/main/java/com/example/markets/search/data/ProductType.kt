package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class ProductType (
    @SerializedName("emistore") val emistore : Emistore?=null,
    @SerializedName("loans") val loans : Loans?=null,
    @SerializedName("essearch") val essearch : Essearch?=null
)