package com.darkabhi.covidproject.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkabhi.covidproject.app.AppConfig
import com.darkabhi.covidproject.data.room.models.DistrictDetail
import com.darkabhi.covidproject.data.room.models.StateDetail
import com.darkabhi.covidproject.home.data.network.repository.CovidRepositoryImpl
import com.darkabhi.covidproject.home.data.network.repository.NewsRepositoryImpl
import com.darkabhi.covidproject.models.NetworkState
import com.darkabhi.covidproject.models.ResultWrapper
import com.darkabhi.covidproject.models.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 5/2/2021.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val covidRepositoryImpl: CovidRepositoryImpl,
    private val newsRepositoryImpl: NewsRepositoryImpl
) : ViewModel() {

    private val _stateDetailResponse = MutableLiveData<NetworkState<StateDetail>>()
    val stateDetailResponse: LiveData<NetworkState<StateDetail>> get() = _stateDetailResponse

    private val _districtDetailResponse = MutableLiveData<NetworkState<List<DistrictDetail>>>()
    val districtDetailResponse: LiveData<NetworkState<List<DistrictDetail>>> get() = _districtDetailResponse

    private val _newsResponse = Channel<State>(Channel.BUFFERED)
    val newsResponse = _newsResponse.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchStateDetail()
            fetchDistrictDetails()
        }
    }

    private fun fetchStateDetail(stateCode: String = "KA") = viewModelScope.launch {
        covidRepositoryImpl.getStateDetail(stateCode).collect {
            _stateDetailResponse.value = it
        }
    }

    private fun fetchDistrictDetails(stateCode: String = "KA") = viewModelScope.launch {
        covidRepositoryImpl.getDistrictDetails(stateCode).collect {
            _districtDetailResponse.value = it
        }
    }

    fun getNews() = viewModelScope.launch {
        _newsResponse.send(State.Loading)
        when (val response = newsRepositoryImpl.getNews("in", "health", AppConfig.NEWS_API_KEY)) {
            is ResultWrapper.GenericError -> {
                _newsResponse.send(State.Failed("${response.code} ${response.error}"))
            }
            ResultWrapper.NetworkError -> {
                _newsResponse.send(State.Failed("Network Error"))
            }
            is ResultWrapper.Success -> {
                _newsResponse.send(State.Success(response.value.articles))
            }
        }
    }
}
