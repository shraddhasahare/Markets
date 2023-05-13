package com.example.markets.search.data

data class RecentDataAction(val action: String, val deleteRequire: Boolean = false, val insertItem: AutoSuggestionDisplayItem? = null)

const val CLEAR = "clear"
const val INSERT = "insert"