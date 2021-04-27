package com.darkabhi.covidproject.app

import android.app.Application
import com.darkabhi.covidproject.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
@HiltAndroidApp
class CovidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}