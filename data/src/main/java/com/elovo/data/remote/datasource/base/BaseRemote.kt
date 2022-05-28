package com.elovo.data.remote.datasource.base

import android.util.Log
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.exception.ApolloHttpException
import com.apollographql.apollo3.exception.ApolloParseException
import com.elovo.data.util.CODE_RESPONSE_KEY
import com.elovo.data.util.MESSAGE_ERROR_KEY
import com.elovo.data.util.RESPONSE_ERROR_KEY
import com.elovo.data.util.UNAUTHORIZED_CODE
import com.elovo.data.util.UNAUTHORIZED_STATUS_CODE
import com.elovo.domain.common.DataSourceException
import com.elovo.domain.common.RavnException.APOLLO_ERROR
import com.elovo.domain.common.RavnException.APOLLO_PARSE_EXCEPTION
import com.elovo.domain.common.RavnException.EXPIRED_TOKEN_EXCEPTION
import com.elovo.domain.common.RavnException.UNKNOWN_ERROR
import com.elovo.domain.common.RavnResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

abstract class BaseRemote(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    @Suppress("UNCHECKED_CAST")
    suspend fun <T : Operation.Data> fetchData(dataProvider: suspend () -> ApolloResponse<T>) =
        try {
            withContext(dispatcher) {
                val result = dataProvider()
                if (result.hasErrors()) {
                    if (result.errors?.first()?.extensions?.get(CODE_RESPONSE_KEY) == UNAUTHORIZED_CODE) {
                        RavnResult.Error(DataSourceException.Unexpected(EXPIRED_TOKEN_EXCEPTION))
                    } else {
                        val responseBody = result
                            .errors?.first()?.extensions?.get(RESPONSE_ERROR_KEY) as? Map<String, Any?>
                        val messageList = responseBody?.get(MESSAGE_ERROR_KEY) as? ArrayList<String>
                        RavnResult.Error(DataSourceException.Server(messageList?.first()))
                    }
                } else {
                    RavnResult.Success(result.data)
                }
            }
        } catch (apolloHttpException: ApolloHttpException) {
            if (apolloHttpException.statusCode == UNAUTHORIZED_STATUS_CODE) {
                Log.w(EXPIRED_TOKEN_EXCEPTION.name, EXPIRED_TOKEN_EXCEPTION.description)
                RavnResult.Error(DataSourceException.Unexpected(EXPIRED_TOKEN_EXCEPTION))
            } else {
                RavnResult.Error(DataSourceException.Unexpected(UNKNOWN_ERROR))
            }
        } catch (e: ApolloParseException) {
            Log.w(
                APOLLO_PARSE_EXCEPTION.name,
                "${APOLLO_PARSE_EXCEPTION.description}: ${e.message}"
            )
            RavnResult.Error(DataSourceException.Unexpected(APOLLO_PARSE_EXCEPTION))
        } catch (apolloException: ApolloException) {
            Log.w(APOLLO_ERROR.name, "${APOLLO_ERROR.description}: ${apolloException.message}")
            RavnResult.Error(DataSourceException.Unexpected(APOLLO_ERROR))
        } catch (e: IOException) {
            Log.w(UNKNOWN_ERROR.name, "${UNKNOWN_ERROR.description}: ${e.message}")
            RavnResult.Error(DataSourceException.Unexpected(UNKNOWN_ERROR))
        }
}
