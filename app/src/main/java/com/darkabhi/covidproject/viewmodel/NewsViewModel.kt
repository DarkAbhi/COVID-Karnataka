package com.darkabhi.covidproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkabhi.covidproject.app.AppConfig
import com.darkabhi.covidproject.data.network.repository.AppRepository
import com.darkabhi.covidproject.models.NewsModel
import com.darkabhi.covidproject.models.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/26/2021.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _newsResponse = MutableLiveData<State<NewsModel>>()
    val newsResponse: LiveData<State<NewsModel>> get() = _newsResponse

    init {
        getNews()
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