package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Variant(
        /* @SerializedName("city_id")
         var cityId: Int? = null,*/
        @SerializedName("special_offers_text")
        var specialOffersText: String? = null,
        @SerializedName("city_special_offers_text")
        var citySpecialOffersText: String? = null,
        @SerializedName("tenure_in_months")
        var tenureInMonths: Int? = null,
        @SerializedName("city_min_price")
        var cityMinPrice: Double? = null,
        @SerializedName("min_price")
        var minPrice: Double? = null,
        @SerializedName("city_zero_downpayment")
        var cityZeroDownpayment: String? = null,
        @SerializedName("striked_off_price")
        var strikedOffPrice: Double? = null,
        @SerializedName("city_hot_deals_flag")
        var cityHotDealsFlag: Int? = null,
        @SerializedName("brand")
        var brand: List<String>? = null,
        @SerializedName("city_striked_off_price")
        var cityStrikedOffPrice: Double? = null,
        @SerializedName("city_special_offer_flag")
        var citySpecialOfferFlag: Int? = null,
        @SerializedName("city_min_emi")
        var cityMinEmi: Int? = null,
        @SerializedName("seller_id")
        var sellerId: String? = null,
        @SerializedName("emi")
        var emi: Int? = null,
        @SerializedName("product_striked_off_price")
        var productStrikedOffPrice: Double? = null,
        @SerializedName("keyword")
        var keyword: String? = null,
        @SerializedName("product_name")
        var productName: String? = null,
        @SerializedName("product_url")
        var productUrl: String? = null,
        @SerializedName("product_small_image_url")
        var productSmallImageUrl: String? = null,
        @SerializedName("product_min_price")
        var productMinPrice: Double? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("modelid")
        var modelid: String? = null,
        @SerializedName("score")
        var score: Float? = null
)