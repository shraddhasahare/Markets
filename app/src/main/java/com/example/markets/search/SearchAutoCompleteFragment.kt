package com.example.markets.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.markets.R
import com.example.markets.baseUtils.BaseFragment
import com.example.markets.baseUtils.OnItemClickListener
import com.example.markets.databinding.FragmentSearchAutoCompleteBinding
import com.example.markets.search.data.AutoSuggestionDisplayItem

class SearchAutoCompleteFragment : BaseFragment(), OnItemClickListener.OnItemClickCallback {
    private var autoSuggestionList: MutableList<AutoSuggestionDisplayItem>? = null
    private val searchViewModel: SearchViewModel by activityViewModels()

    private lateinit var fragmentSearchAutocompleteBinding: FragmentSearchAutoCompleteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSearchAutocompleteBinding = FragmentSearchAutoCompleteBinding.inflate(inflater, container, false)
        return fragmentSearchAutocompleteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            searchViewModel.autoSuggestionResponse.observe(it) { data ->
                if (data?.autoSuggestionResponsePayload != null) {
                    autoSuggestionList =
                        searchViewModel.mapAutoSuggestionItems(data.autoSuggestionResponsePayload)
                    if (autoSuggestionList.isNullOrEmpty()) {
                        searchViewModel.noSearchResult.value = true
                    } else {
                        searchViewModel.noSearchResult.value = false
                        val autoSuggestionAdapter =
                            AutoSuggestionAdapter(autoSuggestionList!!, this, it)
                        fragmentSearchAutocompleteBinding.rvAutoComplete.adapter =
                            autoSuggestionAdapter
                    }
                } else {
                    searchViewModel.noSearchResult.value = true
                }

            }
        }

    }

    override fun onItemClicked(view: View?, position: Int) {
        when (view?.id) {
            R.id.tvAutoSuggestionSearchItemName ->{
                autoSuggestionList?.getOrNull(position)?.let {
                    searchViewModel.autoSuggestionDisplayItem.value = it
                    searchViewModel.clickedForSearchResult.value = true
                    searchViewModel.searchTextDisplayItem.value = it.autoSuggestionText
                }
            }
            R.id.ivSearchArrow ->{
                autoSuggestionList?.getOrNull(position)?.autoSuggestionText?.let {
                    searchViewModel.searchTextDisplayItem.value = it
                }
            }

        }
    }

    fun performSearch(it: String) {
        searchViewModel.getAutoSuggestions(getString(R.string.auto_suggestion_url), it)
    }

}