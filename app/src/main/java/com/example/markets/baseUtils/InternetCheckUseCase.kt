package com.example.markets.baseUtils

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InternetCheckUseCase @Inject constructor(dispatcher: CoroutineDispatcher, val internetCheckHelper: InternetCheckHelper):UseCase<Context,InternetCheckHelper.NetworkStatus>(dispatcher) {
    override suspend fun execute(parameters: Context): Flow<Result<InternetCheckHelper.NetworkStatus>> = flow {
        internetCheckHelper
    }
}