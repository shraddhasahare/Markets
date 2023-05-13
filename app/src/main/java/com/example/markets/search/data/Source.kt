package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Source(
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("highlights")
        val highlights: String? = null,
        @SerializedName("category")
        val category: String? = null,
        @SerializedName("createDate")
        val createDate: String? = null,
        @SerializedName("tags")
        val tags: String? = null,
        @SerializedName("redirection_key")
        val redirection_key: String? = null,
        @SerializedName("redirection_value")
        val redirection_value: String? = null,
        @SerializedName("cta_label")
        val cta_label: String? = null,
        @SerializedName("subcategory")
        val subcategory: String? = null,
        @SerializedName("schemeId")
        val schemeId: String? = null,
        @SerializedName("isGrowthFund")
        val isGrowthFund: String? = null,
        @SerializedName("isDivPayoutFund")
        val isDivPayoutFund: String? = null,
        @SerializedName("isDirectFund")
        val isDirectFund: String? = null,
        @SerializedName("returnFirstYear")
        val returnFirstYear: String? = null,
        @SerializedName("isBeingSold")
        val isBeingSold: String? = null,
        @SerializedName("returnSecondYear")
        val returnSecondYear: String? = null,
        @SerializedName("returnThirdYear")
        val returnThirdYear: String? = null,
        @SerializedName("fundId")
        val fundId: String? = null,
        @SerializedName("minPurchaseAmount")
        val minPurchaseAmount: String? = null,
        @SerializedName("vrRating")
        val vrRating: String? = null,
        @SerializedName("fundRisk")
        val fundRisk: String? = null,
        @SerializedName("fundClass")
        val fundClass: String? = null,
        @SerializedName("fundType")
        val fundType: String? = null,
        @SerializedName("fundSubType")
        val fundSubType: String? = null,
        @SerializedName("imageIcon")
        val logo: String?
)