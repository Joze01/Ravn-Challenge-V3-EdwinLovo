package com.elovo.data.repository.base

import com.elovo.data.mapper.ApiModelMapper
import com.elovo.data.util.RavnResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

abstract class BaseRepository {

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun <T : Any> fetchData(apiDataProvider: RavnResult<T?>): Flow<RavnResult<T>> {
        return flow {
            when (apiDataProvider) {
                is RavnResult.Success -> {
                    apiDataProvider.data?.let {
                        emit(RavnResult.Success(it))
                    }
                }
                is RavnResult.Error -> {
                    emit(RavnResult.Error(apiDataProvider.exception))
                }
                else -> {
                    // Not implemented
                }
            }
        }.onStart { emit(RavnResult.Loading) }
    }

    /**
     * Use this if you need to cache data after fetching it from the api,
     * or retrieve something from cache
     */
    protected suspend fun <T : Any, S : ApiModelMapper<T>> fetchData(
        apiDataProvider: RavnResult<T?>,
        dbSaveAction: suspend (T) -> Unit,
        dbGetAction: suspend () -> S?
    ): Flow<RavnResult<T>> {
        return flow {
            when (apiDataProvider) {
                is RavnResult.Success -> {
                    apiDataProvider.data?.let {
                        emit(RavnResult.Success(it))
                        dbSaveAction(it)
                    }
                }
                is RavnResult.Error -> {
                    dbGetAction()?.let {
                        emit(RavnResult.Success(it.mapToApiModel()))
                    } ?: emit(RavnResult.Error(apiDataProvider.exception))
                }
                else -> {
                    // Not implemented
                }
            }
        }.onStart { emit(RavnResult.Loading) }
    }
}
