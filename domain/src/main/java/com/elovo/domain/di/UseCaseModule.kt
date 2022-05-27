package com.elovo.domain.di

import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.interactor.usecase.GetPeopleUseCase
import com.elovo.domain.interactor.usecase.GetPeopleUseCaseImpl
import com.elovo.domain.interactor.usecase.GetPersonUseCase
import com.elovo.domain.interactor.usecase.GetPersonUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun getPeopleUseCaseProvider(personRepository: PersonRepository): GetPeopleUseCase =
        GetPeopleUseCaseImpl(personRepository)

    @Provides
    @ViewModelScoped
    fun getPersonUseCaseProvider(personRepository: PersonRepository): GetPersonUseCase =
        GetPersonUseCaseImpl(personRepository)
}
