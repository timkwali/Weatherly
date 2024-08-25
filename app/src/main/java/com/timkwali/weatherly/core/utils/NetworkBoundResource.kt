package com.timkwali.weatherly.core.utils

import com.timkwali.weatherly.core.utils.exceptions.handleException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.net.ConnectException

inline fun <ResultType, RequestType> networkBoundResource(
    isNetworkConnected: Boolean,
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if(shouldFetch(data)) {
        emit(Resource.Loading())

        try {
            if(isNetworkConnected) {
                saveFetchResult(fetch())
                query().map { Resource.Success(it) }
            } else {
                throw ConnectException()
            }
        } catch (e: Exception) {
            query().map { Resource.Error(e.handleException().message, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}