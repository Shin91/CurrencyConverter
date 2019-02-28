package com.example.shin.currencyconverter

import android.content.Context
import android.net.ConnectivityManager

class CheckInternetConnection(var context: Context) {


    val isConnected: Boolean
        get() {
            var connected = false

            val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (cm != null) {
                val netInfo = cm.allNetworkInfo

                for (ni in netInfo) {
                    if ((ni.typeName.equals("WIFI", ignoreCase = true) || ni.typeName.equals("MOBILE", ignoreCase = true))
                            && ni.isConnected && ni.isAvailable) {
                        connected = true
                    }

                }
            }

            return connected
        }
}
