package com.example.markets.search

import com.google.gson.annotations.SerializedName

data class FeedTypeUFilter(
        @SerializedName("count")
        val count: String? = null,

        @SerializedName("name")
        val name: String? = null,

        @SerializedName("id")
        val id: String? = null
)