package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Content(
        @SerializedName("_index")
        val index: String? = null,
        @SerializedName("_type")
        val type: String? = null,
        @SerializedName("_id")
        val id: String? = null,
        @SerializedName("_score")
        val score: Double? = null,
        @SerializedName("_source")
        val source: Source? = null
)
