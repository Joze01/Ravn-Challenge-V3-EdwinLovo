package com.elovo.domain.interactor.usecase

import com.elovo.domain.common.RavnResult
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateFavoriteStatusUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) : UpdateFavoriteStatusUseCase {
    override fun invoke(personId: String, isFavorite: Boolean): Flow<RavnResult<Person>> =
        personRepository.updateFavoriteStatus(personId, isFavorite)
}
