package com.elovo.ravnchallenge.presentation.di

import com.elovo.ravnchallenge.presentation.utils.Connectivity
import com.elovo.ravnchallenge.presentation.utils.ConnectivityImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindConnectivity(connectivityImpl: ConnectivityImpl): Connectivity
}
