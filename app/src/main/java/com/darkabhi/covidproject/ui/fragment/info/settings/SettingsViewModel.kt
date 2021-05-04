package com.darkabhi.covidproject.ui.fragment.info.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.darkabhi.covidproject.data.preferences.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 5/2/2021.
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository):ViewModel() {

    val readFromDataStore = dataStoreRepository.readThemeFromDataStore.asLiveData()

    fun saveToDataStore(value: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveTheme(value)
    }

}