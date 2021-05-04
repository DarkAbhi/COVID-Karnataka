package com.darkabhi.covidproject.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.darkabhi.covidproject.data.preferences.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 5/2/2021.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    val readFromDataStore = dataStoreRepository.readThemeFromDataStore.asLiveData()

}