package `in`.bajajfinservmarkets.app.search.data

import com.example.markets.search.data.EsSearchProduct
import com.google.gson.annotations.SerializedName

data class EsSearchResponse(
        @SerializedName("start")
        val start : Int? = null,
        @SerializedName("numberOfProducts")
        val numberOfProducts : Int? = null,
        @SerializedName("products")
        val products : EsSearchProduct? = null
)