package com.elovo.data.di

import com.elovo.data.db.dao.PersonDao
import com.elovo.data.remote.datasource.PeopleRemote
import com.elovo.data.repository.PersonRepositoryImpl
import com.elovo.domain.interactor.repository.PersonRepository
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
        personDao: PersonDao
    ): PersonRepository = PersonRepositoryImpl(peopleRemote, personDao)
}
