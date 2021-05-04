package com.darkabhi.covidproject.ui.activity

import androidx.lifecycle.*
import com.darkabhi.covidproject.app.AppConfig
import com.darkabhi.covidproject.data.network.repository.AppRepository
import com.darkabhi.covidproject.data.preferences.DataStoreRepository
import com.darkabhi.covidproject.models.DistrictData
import com.darkabhi.covidproject.models.NewsModel
import com.darkabhi.covidproject.models.State
import com.darkabhi.covidproject.models.Statewise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 5/2/2021.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository, private val appRepository: AppRepository) : ViewModel() {

    val readFromDataStore = dataStoreRepository.readThemeFromDataStore.asLiveData()

    private val _indiaResponse = MutableLiveData<State<Statewise>>()
    val indiaResponse: LiveData<State<Statewise>> get() = _indiaResponse

    private val _stateResponse = MutableLiveData<State<List<DistrictData>>>()
    val stateResponse: LiveData<State<List<DistrictData>>> get() = _stateResponse

    private val _newsResponse = MutableLiveData<State<NewsModel>>()
    val newsResponse: LiveData<State<NewsModel>> get() = _newsResponse

    init {
        getIndiaDetails()
        getStateDetails()
        getNews()
    }

    private fun getIndiaDetails() = viewModelScope.launch {
        _indiaResponse.value = State.Loading()
        val response = appRepository.getIndiaData()
        if (response.isSuccessful)
            response.body()?.let {
                _indiaResponse.postValue(State.Success(it.statewise[4]))
            }
        else
            _indiaResponse.postValue(State.Failed(response.message()))
    }

    private fun getStateDetails() = viewModelScope.launch {
        _stateResponse.value = State.Loading()
        val response = appRepository.getStateData()
        if (response.isSuccessful)
            response.body()?.let {
                val index = it.indexOfFirst { states ->
                    states.statecode == "KA"
                }
                _stateResponse.postValue(State.Success(it[index].districtData))
            }
        else
            _stateResponse.postValue(State.Failed(response.message()))
    }

    private fun getNews() = viewModelScope.launch {
        _newsResponse.value = State.Loading()
        val response = appRepository.getNews("in", "health", AppConfig.NEWS_API_KEY)
        if (response.isSuccessful)
            response.body()?.let {
                _newsResponse.postValue(State.Success(it))
            }
        else
            _newsResponse.postValue(State.Failed(response.message()))
    }

}