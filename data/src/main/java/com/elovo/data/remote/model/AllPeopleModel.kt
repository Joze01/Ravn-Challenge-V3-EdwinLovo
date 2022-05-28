package com.elovo.data.remote.model

import com.elovo.data.util.mapper.DomainModelMapper
import com.elovo.domain.model.AllPeople
import com.elovo.domain.model.Person

data class AllPeopleModel(
    val people: List<PersonModel?>?,
    val pageInfo: PageInfoModel
) : DomainModelMapper<AllPeople> {

    override fun mapToDomainModel() = AllPeople(
        people = people?.map { it?.mapToDomainModel() ?: Person() } ?: emptyList(),
        pageInfo = pageInfo.mapToDomainModel()
    )
}
