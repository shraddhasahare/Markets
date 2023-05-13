package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class EmiStoreProduct(@SerializedName("product_striked_off_price")
                           var productStrikedOffPrice: Double? = null,
                           @SerializedName("uniqueId")
                           var uniqueId: String? = null,
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
                           @SerializedName("variantTotal")
                           var variantTotal: String? = null,
                           @SerializedName("score")
                           var score: Float? = null,
                           @SerializedName("relevantDocument")
                           var relevantDocument: String? = null,
                           @SerializedName("variantCount")
                           var variantCount: Int? = null,
                           @SerializedName("variants")
                           var variants: List<Variant>? = null
)