package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class QueryParams(
        @SerializedName("log.response") val response : Boolean,
        @SerializedName("original.q") val original : String,
        @SerializedName("module.exclude") val module : String,
        @SerializedName("alternate.op") val alternate : Boolean,
        @SerializedName("req.rm.asterix") val asterix : Boolean,
        @SerializedName("q.op") val op: String,
        @SerializedName("variants") val variant : Boolean,
        @SerializedName("enableTaxonomy") val enableTaxonomy : Boolean,
        @SerializedName("variants.relevant") val relevant : Boolean,
        @SerializedName("q") val q : String,
        @SerializedName("variants.type") val type : String,
        @SerializedName("req.rm.promotionEngine") val promotionEngine : Boolean,
        @SerializedName("user.behaviour") val behaviour : Boolean,
        @SerializedName("enablePopularity") val enablePopularity : Boolean,
        @SerializedName("promotion") val promotion : Boolean)