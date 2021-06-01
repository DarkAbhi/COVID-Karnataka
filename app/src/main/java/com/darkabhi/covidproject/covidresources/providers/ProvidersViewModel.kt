package com.darkabhi.covidproject.covidresources.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.darkabhi.covidproject.data.network.datasource.ResourcesDataSource
import com.darkabhi.covidproject.data.network.repository.ResourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProvidersViewModel @Inject constructor(
    private val resourcesRepository: ResourcesRepository
) :
    ViewModel() {

    fun getData(resourceType: String) = Pager(PagingConfig(pageSize = 16)) {
        ResourcesDataSource(resourcesRepository, resourceType)
    }.flow.cachedIn(viewModelScope)
}
