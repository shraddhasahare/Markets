package com.example.markets.search.data

import com.google.gson.annotations.SerializedName

data class AutoSuggestionResponse(
      @SerializedName("payload") val autoSuggestionResponsePayload : AutoSuggestionResponsePayload? = null
)