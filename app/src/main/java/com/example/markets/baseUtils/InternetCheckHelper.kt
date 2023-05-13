package com.example.markets.baseUtils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class InternetCheckHelper @Inject constructor(@ApplicationContext context: Context) {

    sealed class NetworkStatus {
        object Available : NetworkStatus()
        object Unavailable : NetworkStatus()
    }

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkStatus = callbackFlow<InternetCheckHelper.NetworkStatus> {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                println("onUnavailable")
                trySend(InternetCheckHelper.NetworkStatus.Unavailable).isSuccess
            }

            override fun onAvailable(network: Network) {
                println("onAvailable")
                trySend(InternetCheckHelper.NetworkStatus.Available).isSuccess
            }

            override fun onLost(network: Network) {
                println("onLost")
                trySend(InternetCheckHelper.NetworkStatus.Unavailable).isSuccess
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }.distinctUntilChanged()

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        @RequiresApi(Build.VERSION_CODES.M)
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}
