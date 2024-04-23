package com.istu.schedule

import android.app.Application
import android.content.Intent
import com.istu.schedule.data.model.User
import com.istu.schedule.data.service.notification.NotificationService
import com.istu.schedule.ui.util.NetworkManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var networkManager: NetworkManager

    @Inject
    lateinit var user: User

    override fun onCreate() {
        super.onCreate()
        startService(Intent(this, NotificationService::class.java))
    }
}
