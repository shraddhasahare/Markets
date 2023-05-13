package com.example.markets.baseUtils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import retrofit2.Call
import java.math.BigDecimal
import java.text.DecimalFormat


/**
 * Extention to start activity
 */
fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


/**
 * Retrofit
 */
fun <T> Call<T>.enqueue(callback: CallBackKt<T>.() -> Unit) {
    val callBackKt = CallBackKt<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)
}

fun String.addThousandSeparator(): String {
    if (!TextUtils.isEmpty(this)) {
        try {
            return DecimalFormat("##,##,###").format(BigDecimal(this)) ?: ""
        } catch (e: Exception) {
            return this
        }
    }
    return this
}


