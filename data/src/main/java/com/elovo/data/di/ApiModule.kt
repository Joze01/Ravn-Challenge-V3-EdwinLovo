package com.elovo.data.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import com.elovo.data.BuildConfig
import com.elovo.data.remote.BASE_URL
import com.elovo.data.remote.SwapiApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ApiModule {

    @Provides
    @Singleton
    fun loggingInterceptorProvider(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            (HttpLoggingInterceptor.Level.BODY)
        } else {
            (HttpLoggingInterceptor.Level.BASIC)
        }
        return logging
    }

    @Provides
    @Singleton
    fun okHttpClientProvider(
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addNetworkInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun apolloClientProvider(
        okHttpClient: OkHttpClient,
        @ApplicationContext context: Context
    ): ApolloClient = ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .okHttpClient(okHttpClient)
        .normalizedCache(
            SqlNormalizedCacheFactory(context.applicationContext, "sw_ravn.db")
        )
        .build()

    @Provides
    @Singleton
    fun swapiApiProvider(apolloClient: ApolloClient): SwapiApi = SwapiApi(apolloClient)
}
