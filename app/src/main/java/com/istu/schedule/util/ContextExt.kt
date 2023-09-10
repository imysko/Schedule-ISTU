package com.istu.schedule.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.Uri
import androidx.annotation.RequiresPermission
import com.istu.schedule.data.enums.NetworkStatus
import java.net.InetSocketAddress
import java.net.Socket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun Context.sendMail(to: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (_: ActivityNotFoundException) {
    } catch (_: Throwable) {
    }
}

fun Context.dial(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (_: Throwable) {
    }
}

@get:RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
val Context.networkStatus: Flow<NetworkStatus> get() = getNetworkStatusLollipop(this)

@RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
private fun getNetworkStatusLollipop(context: Context): Flow<NetworkStatus> = callbackFlow {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = object : ConnectivityManager.NetworkCallback() {
        private var availabilityCheckJob: Job? = null

        override fun onUnavailable() {
            availabilityCheckJob?.cancel()
            trySend(NetworkStatus.Unavailable)
        }

        override fun onAvailable(network: Network) {
            availabilityCheckJob = launch {
                send(
                    if (checkAvailability()) NetworkStatus.Available else NetworkStatus.Unavailable
                )
            }
        }

        override fun onLost(network: Network) {
            availabilityCheckJob?.cancel()
            trySend(NetworkStatus.Unavailable)
        }
    }

    val request = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()
    connectivityManager.registerNetworkCallback(request, callback)

    awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
}

private suspend fun checkAvailability(): Boolean = withContext(Dispatchers.IO) {
    try {
        Socket().use {
            it.connect(InetSocketAddress("8.8.8.8", 53))
        }
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}
