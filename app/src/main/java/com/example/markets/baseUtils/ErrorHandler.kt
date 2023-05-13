package com.example.markets.baseUtils

import android.content.Context
import android.text.TextUtils
import com.example.markets.R
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

object ErrorHandler {
    private const val GENERIC_ERROR_MESSAGE = "Something went wrong, please try again."
    private const val NO_INTERNET_MESSAGE =
        "Network connection error, please check your internet connection."
    const val REWARDS_GENERIC_ERROR_MESSAGE = "Sorry!Unfortunately, we are unable to process your request at the moment. Please try after sometime."

    const val SUCCESS = "SUCCESS"
    const val FAILURE = "FAILURE"
    const val PARTIAL_FAILURE = "PARTIAL_SUCCESS"
    const val ERROR_MESSAGE ="Something went wrong!!"

    data class ErrorData(val code: String, val message: String)

    fun handleError(t: Throwable): ErrorData {
        t.printStackTrace()
        return when (t) {
            is HttpException -> {
                ErrorData(t.code().toString(), t.message ?: GENERIC_ERROR_MESSAGE)
            }
            is IOException -> ErrorData(code = "", message = NO_INTERNET_MESSAGE)
            else -> {
                ErrorData(code = "", message = GENERIC_ERROR_MESSAGE)
            }
        }

    }

    fun getResponseErrorMsg(
        context: Context?,
        errorDataList: List<com.example.markets.search.data.ErrorData>?
    ): String {
        return if (errorDataList != null && errorDataList.isNotEmpty()) {
            val errorData = errorDataList[0]
            if (!TextUtils.isEmpty(errorData.errorMessage)) {
                errorData.errorMessage
            } else {
                context?.getString(R.string.something_went_wrong) ?: ""
            }
        } else {
            context?.getString(R.string.something_went_wrong) ?: ""
        }
    }

    fun handleError(errorBody : ResponseBody?,defaultErrorMessage : String? = null) : ErrorData{
        var gson = Gson()
        errorBody?.let {
            try {
                var errorData : com.example.markets.search.data.ErrorData? =
                    gson.fromJson(it.string(),com.example.markets.search.data.ErrorData::class.java)
                errorData?.let {
                    if(!it.errorMessage.isNullOrEmpty()){
                        return ErrorData(it.errorCode,it.errorMessage ?: GENERIC_ERROR_MESSAGE)
                    }
                }
            }catch (e : Exception){
                return ErrorData("",defaultErrorMessage ?: GENERIC_ERROR_MESSAGE)
            }

        }
        return ErrorData("",defaultErrorMessage ?: GENERIC_ERROR_MESSAGE)
    }
}