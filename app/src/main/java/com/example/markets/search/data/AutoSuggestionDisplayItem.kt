package com.example.markets.search.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AutoSuggestionDisplayItem(val autoSuggestionText: String, val root: String, val category: String? = null, val subcategory: String? = null) : Parcelable
