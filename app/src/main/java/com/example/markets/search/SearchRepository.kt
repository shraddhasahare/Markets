package com.example.markets.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.example.markets.BuildConfig
import com.example.markets.baseUtils.ErrorHandler
import com.example.markets.baseUtils.ErrorHandler.ERROR_MESSAGE
import com.example.markets.baseUtils.db.BMarketsDatabase
import com.example.markets.search.data.SearchCategoryResponse
import com.example.markets.search.data.SearchProductsResponse
import okhttp3.ResponseBody
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchApi: SearchApi,
     val marketsDatabase: BMarketsDatabase
) {

     /**
      * fetch cityInfo
      */
     suspend fun getCityInfo(url: String) = searchApi.getCityCode(url)

     /**
      *  fetch search productList list
      */
     suspend fun getSearchProductListApiCall(
          url: String,
          searchQuery: String,
          city_Id: String,
          page: String,
          root: String?,
          category: String?,
          subcategory: String?
     ): SearchProductsResponse {
          val filterParams = ArrayList<String>()
          filterParams.add("emi\$city_id:$city_Id")

          val queryParams = HashMap<String, Any>()
          queryParams["query"] = searchQuery
          category?.let { queryParams["category"] = category }
          subcategory?.let { queryParams["subcategory"] = subcategory }
          queryParams["page"] = page
          queryParams["root"] = root ?: ""
          queryParams["appVersion"] = BuildConfig.VERSION_CODE.toString()
          return searchApi.searchProductsList(url, queryParams, filterParams)
     }

     suspend fun getTrendingSearch(url: String) = searchApi.getTrendingSearch(url)

     @SuppressLint("CheckResult")
     suspend fun getAutoSuggestions(url: String, query: String) = searchApi.getAutoSuggestions(
          url,
          query,
          appVersion = BuildConfig.VERSION_CODE.toString()
     )

      fun getRecentSearches(): LiveData<List<CommonDataCaching>> {
          return marketsDatabase.recentSearchDao().getRecentSearchItem()
     }

     fun getCount(): LiveData<Int> {
          return marketsDatabase.recentSearchDao().getCount()
     }

     suspend fun clearAllRecentSearches() {
          return marketsDatabase.recentSearchDao().deleteRecentSearches()
     }

     fun getErrorMessage(errorBody: ResponseBody?): String {
          errorBody?.let {
               val errorData = ErrorHandler.handleError(it)
               if (errorData.message.isNullOrBlank()) {
                    return (ERROR_MESSAGE)
               }
               return errorData.message
          } ?: return (ERROR_MESSAGE)
     }
}