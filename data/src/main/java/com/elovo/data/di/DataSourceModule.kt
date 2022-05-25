package com.elovo.data.di

import com.elovo.data.datasource.PeopleRemote
import com.elovo.data.datasource.PeopleRemoteImpl
import com.elovo.data.remote.SwapiApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataSourceModule {

    @Provides
    @Singleton
    fun peopleRemoteProvider(api: SwapiApi): PeopleRemote = PeopleRemoteImpl(api)
}
