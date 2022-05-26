package com.elovo.domain.common

sealed class RavnResult<out T> {
    data class Success<out T>(val data: T) : RavnResult<T>()
    data class Error(val exception: DataSourceException) : RavnResult<Nothing>()
    object Loading : RavnResult<Nothing>()
}

inline fun <T : Any?> RavnResult<T>.onSuccess(action: (T) -> Unit): RavnResult<T> {
    if (this is RavnResult.Success) action(data)
    return this
}

inline fun <T : Any?> RavnResult<T>.onError(action: (DataSourceException) -> Unit): RavnResult<T> {
    if (this is RavnResult.Error) action(exception)
    return this
}

inline fun <T : Any?> RavnResult<T>.onLoading(action: () -> Unit): RavnResult<T> {
    if (this is RavnResult.Loading) action()
    return this
}
