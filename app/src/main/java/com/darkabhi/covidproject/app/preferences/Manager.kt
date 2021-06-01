package com.darkabhi.covidproject.app.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 6/1/2021.
 */
class Manager(context: Context) {

    val settings: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun isDarkTheme(): Boolean {
        return settings.getBoolean("app_theme", false)
    }

    fun setDarkTheme(value: Boolean) {
        settings.edit().putBoolean("app_theme", value).apply()
    }
}
