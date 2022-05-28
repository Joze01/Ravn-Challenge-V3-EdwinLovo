package com.elovo.data.remote.model

import com.elovo.data.util.GsonHelper
import com.elovo.data.util.mapper.DomainModelMapper
import com.elovo.domain.model.PageInfo

data class PageInfoModel(
    val startCursor: String?,
    val endCursor: String?,
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean
) : DomainModelMapper<PageInfo> {

    override fun mapToDomainModel(): PageInfo = GsonHelper.parse(this)
}
