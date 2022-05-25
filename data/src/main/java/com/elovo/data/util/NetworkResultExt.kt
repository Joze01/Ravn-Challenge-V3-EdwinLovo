package com.elovo.data.util

inline fun <T, R> RavnResult<T>.onMapping(action: (T) -> RavnResult<R>): RavnResult<R> {
    return when (this) {
        is RavnResult.Success -> data.run(action)
        is RavnResult.Error -> this
        else -> RavnResult.Error(DataSourceException.Unexpected(RavnException.UNKNOWN_ERROR))
    }
}
