package com.elovo.ravnchallenge.presentation.ui.people

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elovo.domain.common.onError
import com.elovo.domain.common.onSuccess
import com.elovo.domain.interactor.usecase.GetPeopleUseCase
import com.elovo.domain.model.Person

class PeopleSource(
    private val getPeopleUseCase: GetPeopleUseCase
) : PagingSource<String, Person>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Person> {
        val endCursor = params.key ?: ""
        var result: LoadResult<String, Person> = LoadResult.Error(Throwable())

        getPeopleUseCase(endCursor).onSuccess { allPeople ->
            result = LoadResult.Page(
                data = allPeople.people,
                prevKey = if (allPeople.pageInfo.hasPreviousPage) allPeople.pageInfo.startCursor else null,
                nextKey = if (allPeople.pageInfo.hasNextPage) allPeople.pageInfo.endCursor else null
            )
        }.onError {
            result = LoadResult.Error(it)
        }
        return result
    }

    override fun getRefreshKey(state: PagingState<String, Person>): String? {
        return state.anchorPosition.toString()
    }
}
