package com.istu.schedule

import android.app.Application
import androidx.work.Configuration
import com.istu.schedule.data.model.User
import com.istu.schedule.ui.util.NetworkManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var networkManager: NetworkManager

    @Inject
    lateinit var user: User

    override val workManagerConfiguration =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
}
