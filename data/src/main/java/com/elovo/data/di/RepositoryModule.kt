package com.elovo.data.di

import com.elovo.data.datasource.PeopleRemote
import com.elovo.data.db.RavnDB
import com.elovo.data.repository.PersonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun personRepositoryProvider(
        peopleRemote: PeopleRemote,
        ravnDB: RavnDB
    ) = PersonRepositoryImpl(peopleRemote, ravnDB)
}
