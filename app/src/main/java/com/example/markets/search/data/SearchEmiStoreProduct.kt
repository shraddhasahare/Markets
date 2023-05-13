package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

class SearchEmiStoreProduct(
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("offer")
        val offer: String? = null,
        @SerializedName("doctype")
        val doctype: String? = null,
        @SerializedName("productCode")
        val productCode: String? = null,
        @SerializedName("feedType")
        val feedType: String? = null,
        @SerializedName("imageUrl")
        val imageUrl: String? = null,
        @SerializedName("autosuggest")
        val auto_suggest: String? = null,
        @SerializedName("productUrl")
        val product_url: String? = null,
        @SerializedName("uniqueId")
        val unique_id: String? = null,
        @SerializedName("navTitle")
        val navTitle: String? = null,
        @SerializedName("Purpose")
        val purpose: String? = null,
        @SerializedName("Profession")
        val profession: String? = null,
        @SerializedName("end_unbxdDate")
        val end_unbxdDate: String? = null
)