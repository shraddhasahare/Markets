package com.example.markets.search

import androidx.lifecycle.*
import com.example.markets.baseUtils.BaseViewModel
import com.example.markets.baseUtils.DashboardViewModelState
import com.example.markets.baseUtils.Data
import com.example.markets.baseUtils.InternetCheckUseCase
import com.example.markets.baseUtils.db.BMarketsDatabase
import com.example.markets.search.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    val marketsDatabase: BMarketsDatabase,
    val internetCheckUseCase: InternetCheckUseCase,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel(internetCheckUseCase)
{

    val noSearchResult = MutableLiveData<Boolean>()
    val searchProductsResponse = MutableLiveData<Data<SearchProductsResponse>>()
    val trendingSearchResponse = MutableLiveData<Data<TrendingSearchResponse>>()
    val cityInfoResponse = MutableLiveData<Data<CityIdResponse>>()
    val autoSuggestionResponse = MutableLiveData<AutoSuggestionResponse?>()
    val recentDataAction = MutableLiveData<RecentDataAction>()
    val autoSuggestionDisplayItem = MutableLiveData<AutoSuggestionDisplayItem>()
    val searchTextDisplayItem = MutableLiveData<String>()
    val clickedForSearchResult = MutableLiveData<Boolean>()

    fun getCityIdInfo(url: String) {
        viewModelScope.launch(dispatcher) {
            try {
                val cityIdResponse = searchRepository.getCityInfo(url)
                cityInfoResponse.postValue(Data(DashboardViewModelState.Success, cityIdResponse))
            } catch (e: Exception) {
                cityInfoResponse.postValue(Data(DashboardViewModelState.Error))
            }
        }
    }

    fun getSearchProductsList(
        url: String,
        searchQuery: String,
        city_Id: String,
        page: String,
        root: String?,
        category: String?,
        subcategory: String?
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = searchRepository.getSearchProductListApiCall(
                    url,
                    searchQuery,
                    city_Id,
                    page,
                    root,
                    category,
                    subcategory
                )
                searchProductsResponse.postValue(Data(DashboardViewModelState.Success, result))
            } catch (e: Exception) {
                searchProductsResponse.postValue(Data(DashboardViewModelState.Error))
            }
        }
    }

    fun getTrendingSearchList(url: String) = viewModelScope.launch(dispatcher) {
        try {
            trendingSearchResponse.postValue(
                Data(
                    DashboardViewModelState.Success,
                    searchRepository.getTrendingSearch(url)
                )
            )
        } catch (e: Exception) {
            trendingSearchResponse.postValue(Data(DashboardViewModelState.Error))
        }

    }

    fun getAutoSuggestions(url: String, query: String) {
        viewModelScope.launch(dispatcher) {
            try {
                autoSuggestionResponse.postValue(searchRepository.getAutoSuggestions(url, query))
            } catch (e: Exception) {
                autoSuggestionResponse.postValue(null)
            }
        }
    }

    fun insertRecentSearchData(commonDataCaching: CommonDataCaching) {
        viewModelScope.launch(dispatcher) {
            marketsDatabase.recentSearchDao().insert(commonDataCaching)
        }
    }

    fun getCount(): LiveData<Int> {
        return searchRepository.getCount()
    }

    fun getRecentSearchedList(): LiveData<List<CommonDataCaching>> {
        return searchRepository.getRecentSearches()
    }

    fun deleteLastRecord(commonDataCaching: CommonDataCaching) {
        viewModelScope.launch(dispatcher) {
            marketsDatabase.recentSearchDao().deleteLastRecord(commonDataCaching)
        }
    }

    fun mapAutoSuggestionItems(autoSuggestionResponsePayload: AutoSuggestionResponsePayload): ArrayList<AutoSuggestionDisplayItem> {
        val autoSuggestionDisplayItems = ArrayList<AutoSuggestionDisplayItem>()
        val features = autoSuggestionResponsePayload.esSearch?.response?.products?.feature
        if (!features.isNullOrEmpty())
            features.map {
                it.source?.title?.let { title ->
                    if (!checkIfAlreadyExistsInSuggestionList(autoSuggestionDisplayItems, title)) {
                        autoSuggestionDisplayItems.add(
                            AutoSuggestionDisplayItem(
                                it.source.title, "essearch", it.source.category, it.source.subcategory
                            )
                        )
                    }
                }

            }
        val products = autoSuggestionResponsePayload.emiStore?.suggestionResponse?.products
        if (!products.isNullOrEmpty())
            products.map {
                it.auto_suggest?.let {
                    if (!checkIfAlreadyExistsInSuggestionList(autoSuggestionDisplayItems, it)) {
                        autoSuggestionDisplayItems.add(
                            AutoSuggestionDisplayItem(
                                it, "emistore"
                            )
                        )
                    }
                }
            }
        return autoSuggestionDisplayItems
    }

    private fun checkIfAlreadyExistsInSuggestionList(
        features: ArrayList<AutoSuggestionDisplayItem>,
        searchResult: String
    ): Boolean {

        for (i in 0 until features.size) {
            val feature = features[i].autoSuggestionText.trim()
            val product = searchResult.trim()
            if (feature.equals(product, true)) {
                return true
            }
        }

        return false
    }

    fun mapSearchResultData(
        subcategory: String?,
        productType: ProductType?
    ): SearchedDisplayResult {
        val searchResults = ArrayList<SearchedDisplayItem>()
        var totalItems = 0
        productType?.let {
            if (subcategory != null) {
                if (subcategory == "loans" || subcategory == "insurance") {
                    totalItems = it.loans?.totalrecords ?: 0
                    if (it.loans?.products?.isNotEmpty() == true)
                        it.loans.products.map { products ->
                            searchResults.add(
                                SearchedDisplayItem(
                                    products = products
                                )
                            )
                        }
                    else {
                        totalItems = it.essearch?.response?.totalrecords ?: 0
                        it.essearch?.products?.map { essearchProducts ->
                            searchResults.add(
                                SearchedDisplayItem(essearchProducts = essearchProducts)
                            )
                        }
                    }
                } else {
                    totalItems = it.essearch?.response?.totalrecords ?: 0
                    it.essearch?.products?.map { essearchProducts ->
                        searchResults.add(
                            SearchedDisplayItem(essearchProducts = essearchProducts)
                        )
                    }
                }
            } else {
                totalItems = it.emistore?.totalrecords ?: 0
                it.emistore?.products?.map { emiStoreProduct ->
                    searchResults.add(
                        SearchedDisplayItem(emiStoreProduct = emiStoreProduct)
                    )
                }
            }
        }
        return SearchedDisplayResult(
            subcategory = subcategory
                ?: "emistore", totalItems = totalItems, searchedDisplayItem = searchResults
        )
    }

    fun mapSearchResultToDisplay(productType: ProductType?) =
        SearchResultModel(productType?.essearch?.products, productType?.emistore?.products)

    suspend fun clearRecentSearches() {
        searchRepository.clearAllRecentSearches()
        recentDataAction.postValue(RecentDataAction("clear"))
    }

}