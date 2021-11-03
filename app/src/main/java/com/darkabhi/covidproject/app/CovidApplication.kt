package com.darkabhi.covidproject.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.darkabhi.covidproject.BuildConfig
import com.darkabhi.covidproject.app.preferences.Manager
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
@HiltAndroidApp
class CovidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        if (Manager(this).isDarkTheme())
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )
        else
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
    }
}
