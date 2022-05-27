package com.elovo.domain.di

import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.interactor.usecase.GetFavoritesUseCase
import com.elovo.domain.interactor.usecase.GetFavoritesUseCaseImpl
import com.elovo.domain.interactor.usecase.GetPeopleUseCase
import com.elovo.domain.interactor.usecase.GetPeopleUseCaseImpl
import com.elovo.domain.interactor.usecase.GetPersonUseCase
import com.elovo.domain.interactor.usecase.GetPersonUseCaseImpl
import com.elovo.domain.interactor.usecase.UpdateFavoriteStatusUseCase
import com.elovo.domain.interactor.usecase.UpdateFavoriteStatusUseCaseImpl
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

    @Provides
    @ViewModelScoped
    fun updateFavoriteStatusUseCaseProvider(
        personRepository: PersonRepository
    ): UpdateFavoriteStatusUseCase = UpdateFavoriteStatusUseCaseImpl(personRepository)

    @Provides
    @ViewModelScoped
    fun getFavoritesUseCaseProvider(personRepository: PersonRepository): GetFavoritesUseCase =
        GetFavoritesUseCaseImpl(personRepository)
}
