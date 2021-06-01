package com.darkabhi.covidproject.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkabhi.covidproject.app.AppConfig
import com.darkabhi.covidproject.home.data.network.repository.CovidRepositoryImpl
import com.darkabhi.covidproject.home.data.network.repository.NewsRepositoryImpl
import com.darkabhi.covidproject.models.ResultWrapper
import com.darkabhi.covidproject.models.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _indiaResponse = MutableStateFlow<State>(State.Empty)
    val indiaResponse: StateFlow<State> get() = _indiaResponse

    private val _stateResponse = MutableStateFlow<State>(State.Empty)
    val stateResponse: StateFlow<State> get() = _stateResponse

    private val _newsResponse = MutableStateFlow<State>(State.Empty)
    val newsResponse: StateFlow<State> get() = _newsResponse

    init {
        getIndiaDetails()
        getStateDetails()
        getNews()
    }

    private fun getIndiaDetails() = viewModelScope.launch {
        _indiaResponse.value = State.Loading
        when (val response = covidRepositoryImpl.getIndiaData()) {
            is ResultWrapper.GenericError -> {
                _indiaResponse.value = State.Failed("${response.code} ${response.error}")
            }
            ResultWrapper.NetworkError -> {
                _indiaResponse.value = State.Failed("Network Error")
            }
            is ResultWrapper.Success -> {
                _indiaResponse.value = State.Success(response.value.statewise[4])
            }
        }
    }

    private fun getStateDetails() = viewModelScope.launch {
        _stateResponse.value = State.Loading
        when (val response = covidRepositoryImpl.getStateData()) {
            is ResultWrapper.GenericError -> {
                _stateResponse.value = State.Failed("${response.code} ${response.error}")
            }
            ResultWrapper.NetworkError -> {
                _stateResponse.value = State.Failed("Network Error")
            }
            is ResultWrapper.Success -> {
                val index = response.value.indexOfFirst { states ->
                    states.statecode == "KA"
                }
                _stateResponse.value = State.Success(response.value[index].districtData)
            }
        }
    }

    private fun getNews() = viewModelScope.launch {
        _newsResponse.value = State.Loading
        when (val response = newsRepositoryImpl.getNews("in", "health", AppConfig.NEWS_API_KEY)) {
            is ResultWrapper.GenericError -> {
                _newsResponse.value = State.Failed("${response.code} ${response.error}")
            }
            ResultWrapper.NetworkError -> {
                _newsResponse.value = State.Failed("Network Error")
            }
            is ResultWrapper.Success -> {
                _newsResponse.value = State.Success(response.value.articles)
            }
        }
    }
}
