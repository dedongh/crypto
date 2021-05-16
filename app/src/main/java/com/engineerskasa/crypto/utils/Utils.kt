package com.engineerskasa.crypto.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class Utils @Inject constructor(private val context: Context) {

    fun isConnectedToInternet() =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .run {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                }?: false
            }
}