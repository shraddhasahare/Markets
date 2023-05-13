package com.example.markets.search.data

import android.annotation.SuppressLint
import android.view.View
import android.widget.RatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.example.markets.R
import com.example.markets.baseUtils.addThousandSeparator

data class SearchedDisplayResult(
    val subcategory: String,
    val totalItems: Int = 0,
    val searchedDisplayItem: MutableList<SearchedDisplayItem>
)


data class SearchedDisplayItem(
    val products: Products? = null,
    val emiStoreProduct: EmiStoreProduct? = null,
    val essearchProducts: EssearchProducts? = null
)

@BindingAdapter("minPurchaseAmount")
fun minPurchaseAmount(textView: AppCompatTextView, amount: String?) {
    textView.text =
        amount?.let { textView.resources.getString(R.string.rupee_symbol) + " " + it.addThousandSeparator() }
}

@BindingAdapter("fundRating")
fun fundRating(ratingBar: RatingBar, rating: String?) {
    if (rating.isNullOrEmpty())
        ratingBar.visibility = View.GONE
    else {
        val toFloat = rating.toFloat()
        if (toFloat > 0) {
            ratingBar.visibility = View.VISIBLE
            ratingBar.rating = toFloat
        } else
            ratingBar.visibility = View.GONE
    }
}

@BindingAdapter("ratingLabelVisibility")
fun ratingLabelVisibility(textView: AppCompatTextView, rating: String?) {
    if (rating.isNullOrEmpty())
        textView.visibility = View.GONE
    else {
        val toFloat = rating.toFloat()
        if (toFloat > 0) {
            textView.visibility = View.VISIBLE
        } else
            textView.visibility = View.GONE
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("roundToTwoDecimal")
fun roundToTwoDecimal(textView: AppCompatTextView, value: String?) {
    if (!value.isNullOrEmpty()) {
        textView.text = "${String.format("%.2f", value.toDouble())}%"
    }
}