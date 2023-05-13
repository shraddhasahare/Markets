package com.example.markets.search

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.markets.R
import com.example.markets.baseUtils.*
import com.example.markets.databinding.ActivitySearchBinding
import com.example.markets.search.data.AutoSuggestionDisplayItem
import com.example.markets.search.data.INSERT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SearchActivity : BaseActivity() {

    private var count: Int? = null
    private lateinit var searchResultFragment: SearchResultFragment
    private lateinit var autoCompleteFragment: SearchAutoCompleteFragment
    private var recentSearchList: MutableList<CommonDataCaching>? = null

    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var activitySearchNewBinding: ActivitySearchBinding
    private var cityId: String? = null
    private var progressDialog: Dialog? = null
    private var isRecentTextClicked: Boolean = false

    override fun getViewModel(): BaseViewModel {
        return searchViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (hasActivityRestored)
//            return
        activitySearchNewBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)

        activitySearchNewBinding.imgBack.setOnClickListener { onBackPressed() }

        showSearchAnimation()
        activitySearchNewBinding.RlFadingTv.setOnClickListener {
            hideSearchAnimation()
            activitySearchNewBinding.etSearch.requestFocus()
            activitySearchNewBinding.etSearch.isFocusableInTouchMode = true
            activitySearchNewBinding.etSearch.showKeyboard()
        }

        activitySearchNewBinding.imgSearch.setOnClickListener {
            hideSearchAnimation()
            activitySearchNewBinding.etSearch.requestFocus()
            activitySearchNewBinding.etSearch.isFocusableInTouchMode = true
            activitySearchNewBinding.etSearch.showKeyboard()
        }

        val arraySearchText = this.resources.getStringArray(R.array.searchText)

        animateText(activitySearchNewBinding.ftvSearch, arraySearchText)

        addSearchesFragment()
        initObserver()
        getCityInfo()
        getSearchSequence(intent.extras?.getBoolean("for_invest") ?: false)
        initSearchListener()
        observeNavigateToHomeLob()
    }

    private fun animateText(textView: TextView, texts: Array<String>) {
        val fadeInDuration = 1000
        val timeBetween = 1500
        val fadeOutDuration = 1200

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.ftv_slide_up)
        fadeIn.interpolator = DecelerateInterpolator()
        fadeIn.duration = fadeInDuration.toLong()

        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.ftv_slide_out)
        fadeOut.interpolator = DecelerateInterpolator()
        fadeOut.startOffset = timeBetween.toLong()
        fadeOut.duration = fadeOutDuration.toLong()

        val animation = AnimationSet(false)
        val animation2 = AnimationSet(false)
        animation.addAnimation(fadeIn)
        animation2.addAnimation(fadeOut)
        var i = 0

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                if (i < 6) {
                    textView.text = texts[i]
                    textView.visibility = View.VISIBLE
                } else {
                    textView.text = texts[0]
                    textView.visibility = View.VISIBLE
                    i = 0
                }
            }

            override fun onAnimationEnd(animation: Animation?) {
                textView.startAnimation(animation2)
            }
            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
        animation2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                i++
            }
            override fun onAnimationEnd(p0: Animation?) {
                textView.visibility = View.GONE
                textView.startAnimation(animation)
            }
            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
        textView.startAnimation(animation)
    }

    private fun initSearchListener() {

        activitySearchNewBinding.etSearch.addTextChangedListener(object : TextWatcher {
            private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
            private var searchJob: Job? = null
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchJob?.cancel()
                searchJob = coroutineScope.launch {
                    s?.toString()?.let {
                        delay(300)
                        if (it.isNotEmpty()) {
                            if (isRecentTextClicked) return@let
                            showSearchView(R.id.search_autoComplete)
                            supportFragmentManager.findFragmentById(R.id.search_autoComplete)
                                ?.let { it2 ->
                                    if (it2 is SearchAutoCompleteFragment) it2.performSearch(it)
                                }
                        } else
                            showSearchLandingScreen()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun showSearchView(id: Int) {
        if (activitySearchNewBinding.searchContainer.findViewById<View>(id).visibility == View.VISIBLE)
            return
        val childCount = activitySearchNewBinding.searchContainer.childCount
        for (i in 0 until childCount) {
            val v: View = activitySearchNewBinding.searchContainer.getChildAt(i)
            v.visibility = if (v.id == id) View.VISIBLE else View.GONE
        }
    }

    private fun addSearchesFragment() {
        autoCompleteFragment = SearchAutoCompleteFragment()
        val fragmentView = LayoutInflater.from(this)
            .inflate(R.layout.frame_layout_home_tab, LinearLayout(this), false)
        activitySearchNewBinding.searchContainer.addView(fragmentView)
        fragmentView.id = R.id.search_autoComplete
        supportFragmentManager.beginTransaction().replace(fragmentView.id, autoCompleteFragment)
            .commit()
        searchResultFragment = SearchResultFragment()
        val fragmentView2 = LayoutInflater.from(this)
            .inflate(R.layout.frame_layout_home_tab, LinearLayout(this), false)
        activitySearchNewBinding.searchContainer.addView(fragmentView2)
        fragmentView2.id = R.id.search_result
        supportFragmentManager.beginTransaction().replace(fragmentView2.id, searchResultFragment)
            .commit()
        hideSearchResultFragments()
    }

    private fun hideSearchResultFragments() {
        val view: View =
            activitySearchNewBinding.searchContainer.findViewById(R.id.search_autoComplete)
        val view2: View = activitySearchNewBinding.searchContainer.findViewById(R.id.search_result)
        view.visibility = View.GONE
        view2.visibility = View.GONE
    }

    private fun getSearchSequence(isForInvest: Boolean) {
         progressDialog = showCommonProgressDialog(this)
         progressDialog?.show()
         searchViewModel.getTrendingSearchList(
             if (isForInvest) getString(R.string.trending_search_invest) else getString(
                 R.string.trending_search
             )
         )
    }

    private fun getCityInfo() {
        searchViewModel.getCityIdInfo(getString(R.string.get_city_code))
    }

    private fun initObserver() {
        searchViewModel.clickedForSearchResult.observe(this) {
            Handler(Looper.getMainLooper()).postDelayed({
                isRecentTextClicked = false
            }, 500)
        }
        searchViewModel.cityInfoResponse.observe(this) { data ->
            when (data?.DashboardViewModelState) {
                DashboardViewModelState.Success -> {
                    data.data?.data!!.cityId.let {
                        cityId = it
                    }
                }
                else -> {
                }
            }
        }
        searchViewModel.trendingSearchResponse.observe(this) { data ->
            run {
                progressDialog?.dismiss()
                when (data?.DashboardViewModelState) {
                    DashboardViewModelState.Success -> {
                        data.data?.widgetComponentDetails?.forEach { item ->
                            item.let {
                                    when (item.widgetId) {



                                    }
                            }
                        }
                    }
                    DashboardViewModelState.Error -> {
                    }
                    DashboardViewModelState.Loading -> {
                    }
                    else -> {}
                }
            }
        }
         searchViewModel.recentDataAction.observe(this
         ) {
             if (it.action == INSERT) {
                 if ((count ?: 0) < 10) {
                     val recentSearch = CommonDataCaching(
                         0, it.insertItem?.autoSuggestionText
                             ?: "", it.insertItem?.category
                             ?: "", it.insertItem?.subcategory, it.insertItem?.root
                     )
                     recentSearch.let { it2 -> searchViewModel.insertRecentSearchData(it2) }
                 } else {
                     recentSearchList?.getOrNull(count!! - 1)?.let { it2 ->
                         searchViewModel.deleteLastRecord(it2)
                         val recentSearch = CommonDataCaching(
                             0, it.insertItem?.autoSuggestionText
                                 ?: "", it.insertItem?.category
                                 ?: "", it.insertItem?.subcategory, it.insertItem?.root
                         )
                         searchViewModel.insertRecentSearchData(recentSearch)
                     }
                 }
             }
         }
        searchViewModel.autoSuggestionDisplayItem.observe(this) {
            hideSearchAnimation()
            isRecentTextClicked = true
            getSearchResult(it)
        }
        searchViewModel.searchTextDisplayItem.observe(this) {
            hideSearchAnimation()
            activitySearchNewBinding.etSearch.setText(it)
            activitySearchNewBinding.etSearch.setSelection(activitySearchNewBinding.etSearch.length())
        }

        searchViewModel.getCount().observe(this) {
            count = it
        }
        searchViewModel.noSearchResult.observe(this) { it ->
            if (it) {
                isRecentTextClicked = false
                onBackPressed()
                val existView =
                    activitySearchNewBinding.searchContainer.findViewById<View>(R.id.search_no_data)
                if (existView == null) {
                    val noDataView = LayoutInflater.from(this)
                        .inflate(R.layout.no_search_result, LinearLayout(this), false)
                    noDataView.id = R.id.search_no_data
                    activitySearchNewBinding.searchContainer.addView(noDataView, 0)
                }
            } else {
                activitySearchNewBinding.searchContainer.findViewById<View>(R.id.search_no_data)
                    ?.let {
                        activitySearchNewBinding.searchContainer.removeView(it)
                    }
            }
        }

    }

    private fun hideViewIfRequire(fragmentView: View?) {
        if (activitySearchNewBinding.searchContainer.findViewById<View>(R.id.search_autoComplete).visibility == View.VISIBLE ||
            activitySearchNewBinding.searchContainer.findViewById<View>(R.id.search_result).visibility == View.VISIBLE
        )
            fragmentView?.visibility = View.GONE
    }

    private fun getSearchResult(autoSuggestionDisplayItem: AutoSuggestionDisplayItem) {
        showSearchView(R.id.search_result)
        supportFragmentManager.findFragmentById(R.id.search_result)?.let {
            if (it is SearchResultFragment) it.performSearchResult(
                cityId,
                autoSuggestionDisplayItem
            )
        }
    }

    override fun onBackPressed() {
        if (activitySearchNewBinding.searchContainer.findViewById<View>(R.id.search_autoComplete).visibility == View.VISIBLE
            || activitySearchNewBinding.searchContainer.findViewById<View>(R.id.search_result).visibility == View.VISIBLE
        ) {
            showSearchAnimation()
            activitySearchNewBinding.etSearch.text.clear()
            activitySearchNewBinding.etSearch.hideKeyboard()
            showSearchLandingScreen()
        } else {
            super.onBackPressed()
        }
    }

    private fun showSearchLandingScreen() {
        isRecentTextClicked = false
        val childCount = activitySearchNewBinding.searchContainer.childCount
        for (i in 0 until childCount) {
            val v: View = activitySearchNewBinding.searchContainer.getChildAt(i)
            v.visibility =
                if (v.id == R.id.search_autoComplete || v.id == R.id.search_result) View.GONE else View.VISIBLE
        }
    }

    fun observeNavigateToHomeLob() {
       /* MarketsApplication.Companion.applicationContext()
            .getTokenManager().navigateToHomeLob.observe(this,
                androidx.lifecycle.Observer {
                    if (!isFinishing) {
                        finish()
                    }
                }) */
    }

    private fun hideSearchAnimation() {
        activitySearchNewBinding.etSearch.visibility = View.VISIBLE
        activitySearchNewBinding.RlFadingTv.visibility = View.GONE
        activitySearchNewBinding.imgSearch.visibility = View.GONE
    }

    private fun showSearchAnimation() {
        activitySearchNewBinding.etSearch.visibility = View.GONE
        activitySearchNewBinding.RlFadingTv.visibility = View.VISIBLE
        activitySearchNewBinding.imgSearch.visibility = View.VISIBLE
    }

}