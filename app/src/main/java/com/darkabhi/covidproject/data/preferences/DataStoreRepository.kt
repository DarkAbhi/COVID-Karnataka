package com.darkabhi.covidproject.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.darkabhi.covidproject.app.AppConfig.PREFERENCE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 5/2/2021.
 */


// App preferences are stored here
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class DataStoreRepository (context: Context){
    private val dataStore = context.dataStore

    private object PreferenceKeys {
        val theme = booleanPreferencesKey("theme")
    }

    /**
     *  Get & set theme
     *  @param status
     */
    suspend fun saveTheme(status: Boolean) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.theme] = status
        }
    }

    val readThemeFromDataStore: Flow<Boolean> = dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Timber.e(exception)
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preference ->
                val themeStatus = preference[PreferenceKeys.theme] ?: false
                themeStatus
            }
}