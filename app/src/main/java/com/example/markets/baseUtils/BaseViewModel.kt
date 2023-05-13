package com.example.markets.baseUtils

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(private val internetCheckUseCase: InternetCheckUseCase) :
    ViewModel() {

    suspend fun getNetworkCall() = internetCheckUseCase.internetCheckHelper.networkStatus
}