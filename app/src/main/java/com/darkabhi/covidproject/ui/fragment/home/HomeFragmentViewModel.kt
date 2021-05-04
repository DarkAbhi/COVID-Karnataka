package com.darkabhi.covidproject.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkabhi.covidproject.data.network.repository.AppRepository
import com.darkabhi.covidproject.models.DistrictData
import com.darkabhi.covidproject.models.State
import com.darkabhi.covidproject.models.Statewise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _indiaResponse = MutableLiveData<State<Statewise>>()
    val indiaResponse: LiveData<State<Statewise>> get() = _indiaResponse

    private val _stateResponse = MutableLiveData<State<List<DistrictData>>>()
    val stateResponse: LiveData<State<List<DistrictData>>> get() = _stateResponse

    init {
        getIndiaDetails()
        getStateDetails()
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

}