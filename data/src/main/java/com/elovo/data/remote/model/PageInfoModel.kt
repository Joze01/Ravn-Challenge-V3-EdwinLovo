package com.elovo.data.remote.model

import com.elovo.data.mapper.DomainModelMapper
import com.elovo.domain.model.PageInfo

data class PageInfoModel(
    val startCursor: String?,
    val endCursor: String?,
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean
) : DomainModelMapper<PageInfo> {

    override fun mapToDomainModel() = PageInfo(
        startCursor = startCursor,
        endCursor = endCursor,
        hasNextPage = hasNextPage,
        hasPreviousPage = hasPreviousPage
    )
}
