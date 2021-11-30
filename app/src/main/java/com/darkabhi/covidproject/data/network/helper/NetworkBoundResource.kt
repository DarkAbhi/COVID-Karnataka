package com.darkabhi.covidproject.data.network.helper

import com.darkabhi.covidproject.models.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 6/14/2021.
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(NetworkState.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map {
                NetworkState.Success(it)
            }
        } catch (throwable: Throwable) {
            query().map { NetworkState.Error(throwable, it) }
        }
    } else {
        query().map { NetworkState.Success(it) }
    }
    emitAll(flow)
}
