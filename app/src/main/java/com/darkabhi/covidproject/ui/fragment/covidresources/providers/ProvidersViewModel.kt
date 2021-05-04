package com.darkabhi.covidproject.ui.fragment.covidresources.providers

import androidx.lifecycle.ViewModel
import com.darkabhi.covidproject.data.network.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class ProvidersViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    @ExperimentalCoroutinesApi
    fun fetchAmbulanceData(collectionName:String) = appRepository.getAmbulanceServiceData(collectionName)
}