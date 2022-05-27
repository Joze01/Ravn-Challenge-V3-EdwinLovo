package com.elovo.ravnchallenge.presentation.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConnectivityImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : Connectivity {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val monitorLiveNetwork: LiveData<Boolean>
        get() = mIsNetworkActive

    private val mIsNetworkActive: MutableLiveData<Boolean> =
        object : MutableLiveData<Boolean>(false) {
            override fun onActive() {
                super.onActive()
                val builder = NetworkRequest.Builder()
                connectivityManager.registerNetworkCallback(
                    builder
                        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        .build(),
                    networkCallback
                )
            }

            override fun onInactive() {
                super.onInactive()
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            CoroutineScope(Dispatchers.IO).launch {
                mIsNetworkActive.postValue(InternetCheckHelper.execute(network.socketFactory))
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            mIsNetworkActive.postValue(false)
        }

        // This will be called after onAvailable only on Android 8 and above
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)

            val hasInternet =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

            CoroutineScope(Dispatchers.IO).launch {
                val canConnect =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    } else {
                        InternetCheckHelper.execute(network.socketFactory)
                    }
                mIsNetworkActive.postValue(hasInternet && canConnect)
            }
        }
    }

    override fun hasNetworkAccess(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                } ?: false
            }
        }
    }
}
