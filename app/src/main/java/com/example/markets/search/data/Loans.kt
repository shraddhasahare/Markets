package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class Loans(
        @SerializedName("suggested_search_keyword")
        val suggested_search_suggested_search_keywordword : String?=null,

        @SerializedName("totalrecords")
        val totalrecords : Int?=null,

        @SerializedName("products")
        val products : MutableList<Products>?=null

       /* @SerializedName("filters")
        val filters : Filters?=null*/
)