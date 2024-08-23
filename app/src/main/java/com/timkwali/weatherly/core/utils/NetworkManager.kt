package com.timkwali.weatherly.core.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject

class NetworkManager @Inject constructor(
    private val application: Application
) {

    fun isConnected(): Boolean {
        val connectivityManager = application.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return isNetworkAvailable(connectivityManager)

    }

    private fun isNetworkAvailable(connectivityManager: ConnectivityManager): Boolean {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true ||
                    capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true ||
                    capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true
        } else {
            try {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                return (activeNetworkInfo != null && activeNetworkInfo.isConnected)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }
}