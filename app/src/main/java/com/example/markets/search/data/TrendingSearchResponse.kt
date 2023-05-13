package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class TrendingSearchResponse(
        @SerializedName("widget_header")
        val widgetHeader: String? = null,
        @SerializedName("widget_id")
        val widgetId: String? = null,
        @SerializedName("priority")
        val priority: Int? = null,
        @SerializedName("sectionName")
        val sectionName: String? = null,
        @SerializedName("backgroundImage")
        val backgroundImage: String? = null,
        @SerializedName("showCta")
        val showCta: String? = null,
        @SerializedName("ctaText")
        val ctaText: String? = null,
        @SerializedName("ctaRedirectionKey")
        val ctaRedirectionKey: String? = null,
        @SerializedName("ctaRedirectionValue")
        val ctaRedirectionValue: String? = null,
        @SerializedName("widgetComponentDetails")
        val widgetComponentDetails: List<WidgetComponentDetail>? = null
)