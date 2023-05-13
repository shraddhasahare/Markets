package com.example.markets.search

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.example.markets.R
import com.example.markets.baseUtils.BaseFragment
import com.example.markets.baseUtils.DashboardViewModelState
import com.example.markets.baseUtils.showCommonProgressDialog
import com.example.markets.databinding.FragmentSearchResultBinding
import com.example.markets.search.data.*

class SearchResultFragment : BaseFragment() {
    private val searchViewModel: SearchViewModel by activityViewModels()
    private lateinit var fragmetSearchResultBinding: FragmentSearchResultBinding
    private var cityId: String? = null
    private var progressDialog: Dialog? = null
    private var currentPage: Int = 0
    private var autoSuggestionDisplayItem: AutoSuggestionDisplayItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmetSearchResultBinding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return fragmetSearchResultBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { it1 ->
            searchViewModel.searchProductsResponse.observe(it1) { data ->
                progressDialog?.dismiss()
                when (data?.DashboardViewModelState) {
                    DashboardViewModelState.Success -> {
                        val results =
                            searchViewModel.mapSearchResultToDisplay(data.data?.payload?.productType)
                        if (results.financialProducts.isNullOrEmpty() && results.emiStoreProduct.isNullOrEmpty()) {
                            searchViewModel.noSearchResult.value = true
                        } else {
                            searchViewModel.noSearchResult.value = false
                            cacheSearch()
                        }
                    }
                    DashboardViewModelState.Error -> {
                        searchViewModel.noSearchResult.value = true
                    }
                    DashboardViewModelState.Loading -> {}
                    else -> {}
                }
            }
        }

    }

    fun performSearchResult(cityId: String?, autoSuggestionDisplayItem: AutoSuggestionDisplayItem) {
        fragmetSearchResultBinding.searchResultContainer.removeAllViews()
        this.cityId = cityId
        this.autoSuggestionDisplayItem = autoSuggestionDisplayItem
        currentPage = 0
        activity?.let { activity ->
            progressDialog = showCommonProgressDialog(activity)
            progressDialog?.show()
        }
        getSearchProductList()
    }

    private fun getSearchProductList() {
        autoSuggestionDisplayItem?.let {
            searchViewModel.getSearchProductsList(
                getString(R.string.search_product_url), it.autoSuggestionText, cityId
                    ?: "", "$currentPage", it.root, it.category, it.subcategory
            )
        }
    }

    private fun cacheSearch() {
        searchViewModel.recentDataAction.value =
            RecentDataAction(action = INSERT, insertItem = autoSuggestionDisplayItem)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                activity?.finish()
            }
        }

    fun onEmiProductClick(product: EmiStoreProduct) {
//        Router.navigateToEmiStoreUrl(requireActivity(), product.productUrl, TokenManager)
    }

    fun onFinancialProductClick(product: EssearchProducts) {
        handleRedirection(
            product._source?.cta_label,
            product._source?.redirection_key,
            product._source?.redirection_value,
            ""
        )
    }

    private fun handleRedirection(
        appCtaLabel: String?,
        appRedirectionKey: String?,
        appRedirectionValue: String?,
        productCode: String? = null
    ) {

        val bundle = Bundle().apply { putString(appRedirectionKey, appRedirectionValue) }

     /*   val bundle2 = Bundle()
        bundle2.putString(appRedirectionKey, appRedirectionValue)
        bundle2.putString(KEY_UTM_MEDIUM, "")
        bundle2.putString(KEY_UTM_CAMPAIGN, "Search")
        bundle2.putString(KEY_UTM_TERM, autoSuggestionDisplayItem?.subcategory ?: "")*/
//        Router(activity!!, bundle2)
    }


}