package com.example.markets.search.data

import com.example.markets.search.FeedTypeUFilter
import com.google.gson.annotations.SerializedName

data class Filters (

        @SerializedName("feedType_uFilter")
        val feedType_uFilter : List<FeedTypeUFilter>?=null,

        @SerializedName("filters_offer")
        val filters_offer : List<FiltersOffer>?=null,

        @SerializedName("filters_profession")
        val filters_profession : List<FiltersProfession>?=null
)