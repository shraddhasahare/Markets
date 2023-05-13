package com.example.markets.search.data

import com.example.markets.search.FeedTypeUFilter
import com.google.gson.annotations.SerializedName

data class SearchCategoryResponse(
        @SerializedName("feedType_uFilter")
        val feedTypeFilter: FeedTypeUFilter? = null
)