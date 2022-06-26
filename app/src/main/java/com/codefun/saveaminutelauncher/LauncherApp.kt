package com.codefun.saveaminutelauncher

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Agam on 20,June,2022
 */

@HiltAndroidApp
class LauncherApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}