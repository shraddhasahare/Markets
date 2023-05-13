package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Products(
        @SerializedName("id")
        val id: String? = null,

        @SerializedName("product_name")
        val product_name: String? = null,

        @SerializedName("title")
        val title: String? = null,

        @SerializedName("keyword")
        val keywordword: String? = null,

        @SerializedName("feed_type")
        val feed_type: String? = null,

        @SerializedName("product_url")
        val product_url: String? = null,

        @SerializedName("product_small_image_url")
        val product_small_image_url: String? = null,

        @SerializedName("description")
        val description: String? = null,

        @SerializedName("code")
        val code: String? = null,

        @SerializedName("offer_tags")
        val offer_tags: List<String>? = null,

        @SerializedName("app_Website_Product_name")
        val app_Website_Product_name: String? = null,

        @SerializedName("app_Redirection_Key")
        val app_Redirection_Key: String? = null,

        @SerializedName("app_Redirection_Value")
        val app_Redirection_Value: String? = null,

        @SerializedName("app_Highlights")
        val app_Highlights: String? = null,

        @SerializedName("app_CTA_Label")
        val app_CTA_Label: String? = null,

        @SerializedName("app_IsEstore")
        val app_IsEstore: Int? = null,

        @SerializedName("app_Offer_label")
        val app_Offer_label: String? = null,

        @SerializedName("sort_order")
        val sort_order: Int? = null,

        @SerializedName("filters_profession")
        val filters_profession: FiltersProfession? = null,

        @SerializedName("filters_offer")
        val filters_offer: FiltersOffer? = null,

        @SerializedName("filters_category")
        val filters_category: FiltersCategory? = null,

        @SerializedName("filters_insuringfor")
        val filters_insuringfor: FiltersInsuringfor? = null,

        @SerializedName("filters_purpose")
        val filters_purpose: FiltersPurpose? = null,

        @SerializedName("timestamp")
        val timestamp : Long
)