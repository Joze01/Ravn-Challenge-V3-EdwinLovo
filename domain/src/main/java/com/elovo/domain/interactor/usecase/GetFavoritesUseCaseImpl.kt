package com.elovo.domain.interactor.usecase

import com.elovo.domain.common.RavnResult
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) : GetFavoritesUseCase {
    override fun invoke(): Flow<RavnResult<List<Person>>> = personRepository.getAllFavorites()
}
