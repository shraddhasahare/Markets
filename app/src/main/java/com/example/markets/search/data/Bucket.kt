package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Bucket(
        @SerializedName("values")
        var values: List<String>? = null,
        @SerializedName("position")
        var position: Int?= null,
        @SerializedName("displayName")
        var displayName: String? = null,
        @SerializedName("filterParam")
        var filterParam:String? = null,
        @SerializedName("level")
        var level:Int? = null,
        @SerializedName("multiLevelField")
        var multiLevelField:String? = null
)