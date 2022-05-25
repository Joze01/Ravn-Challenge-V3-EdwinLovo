package com.elovo.data.util

sealed class DataSourceException(
    val exception: Any?
) : RuntimeException() {
    class Unexpected(exception: RavnException) : DataSourceException(exception)
    class Server(error: String?) : DataSourceException(error)
}
