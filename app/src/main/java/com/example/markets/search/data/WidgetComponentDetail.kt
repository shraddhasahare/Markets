package com.example.markets.search.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WidgetComponentDetail(
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("widget_id")
        val widgetId: String? = null,
        @SerializedName("sortOrder")
        val sortOrder: String? = null,
        @SerializedName("tag")
        val tag: String? = null,
        @SerializedName("imageText")
        val imageText: String? = null,
        @SerializedName("imageIcon")
        val imageIcon: String? = null,
        @SerializedName("imageIcon2")
        val imageIcon2: String? = null,
        @SerializedName("redirectionKey")
        val redirectionKey: String? = null,
        @SerializedName("redirectionValue")
        val redirectionValue: String? = null,
        @SerializedName("redirectionKey1")
        val redirectionKey1: String? = null,
        @SerializedName("redirectionValue1")
        val redirectionValue1: String? = null,
        @SerializedName("offer_text")
        val offerText: String? = null,
        @SerializedName("widgetComponentDetails")
        val widgetComponentDetails: List<WidgetComponentDetail>,

        @field:SerializedName("tagBgColor")
        val tagBgColor: String? = null,

        @field:SerializedName("tagTextColor")
        val tagTextColor: String? = null
): Serializable