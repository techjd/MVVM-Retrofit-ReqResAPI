package com.techjd.utils

import android.content.Context
import android.net.ConnectivityManager

@Suppress("DEPRECATION")
object InternetConnection {
    fun checkConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connMgr != null) {
            val activeNetworkInfo = connMgr.activeNetworkInfo
            if (activeNetworkInfo != null) {
                return if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    true
                } else activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
            }
        }
        return false
    }
}