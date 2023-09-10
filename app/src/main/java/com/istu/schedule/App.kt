package com.istu.schedule

import android.app.Application
import com.istu.schedule.data.model.NetworkManager
import com.istu.schedule.data.model.User
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var networkManager: NetworkManager

    @Inject
    lateinit var user: User
}
