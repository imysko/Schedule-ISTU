package com.istu.schedule.data.model

import android.content.Context
import android.util.Log
import com.istu.schedule.util.networkStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

@Singleton
class NetworkManager @Inject constructor(@ApplicationContext private val _context: Context) {

    val changedNetworkStatus = _context.networkStatus
        .shareIn(CoroutineScope(Dispatchers.IO), SharingStarted.Eagerly, 1)

    init {
        CoroutineScope(Dispatchers.IO).launch {
            changedNetworkStatus.collect {
                Log.e("NetworkStatus", it.toString())
            }
        }
    }
}
