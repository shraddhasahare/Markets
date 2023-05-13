package com.example.markets.baseUtils

import androidx.lifecycle.MutableLiveData


sealed class DashboardViewModelState {
    object Loading : DashboardViewModelState()
    object Success : DashboardViewModelState()
    object Error : DashboardViewModelState()
}

data class Data<T>(
    val DashboardViewModelState: DashboardViewModelState,
    val data: T? = null,
    val stringState: String? = null,
    var boolState: Boolean = false,
    var intState: Int = 0,
    var errorData: ErrorHandler.ErrorData?=null,
    val header: String? = null
)

fun <T> MutableLiveData<Data<T>>.setSuccess(data: T? = null) {
    value = Data(DashboardViewModelState.Success, data)
}
fun <T> MutableLiveData<Data<T>>.setSuccess(data: T? = null,header: String?) {
    value = Data(DashboardViewModelState.Success, data,"",false,0,null,header)
}

fun <T> MutableLiveData<Data<T>>.setLoading() {
    value = Data(DashboardViewModelState.Loading, value?.data)
}

fun <T> MutableLiveData<Data<T>>.setError(message: String? = null) {
    value = Data(DashboardViewModelState.Error, value?.data, message)
}
fun <T> MutableLiveData<Data<T>>.setError(message: ErrorHandler.ErrorData? = null) {
    value = Data(DashboardViewModelState.Error, value?.data, "",false,0,message)
}
fun <T> MutableLiveData<Data<T>>.setError(message: ErrorHandler.ErrorData? = null,header: String?) {
    value = Data(DashboardViewModelState.Error, value?.data, "",false,0,message,header)
}
fun <T> MutableLiveData<Data<T>>.getData(): T? = value?.data
