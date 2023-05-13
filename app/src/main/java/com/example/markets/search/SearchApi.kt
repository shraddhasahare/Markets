package com.example.markets.search

import com.example.markets.search.data.*
import retrofit2.Call
import retrofit2.http.*

interface SearchApi {
    @GET
    suspend fun getCityCode(@Url url: String): CityIdResponse

    @GET
    fun getSearchCategory(
        @Url url: String,
        @Query("q") searchQuery: String
    ): Call<SearchCategoryResponse>

    @GET
    suspend fun searchProductsList(
        @Url url: String,
        @QueryMap queryParams: HashMap<String, Any>,
        @Query("filter") filterParams: ArrayList<String>): SearchProductsResponse

    @GET
    suspend fun getTrendingSearch(@Url url: String): TrendingSearchResponse

    @GET
    suspend fun getAutoSuggestions(
        @Url url: String,
        @Query("query") autoSuggestion: String,
        @Query("appVersion") appVersion: String
    ): AutoSuggestionResponse
}