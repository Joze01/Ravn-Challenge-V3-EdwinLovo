package com.elovo.data.remote.model

data class PageInfoModel(
    val startCursor: String?,
    val endCursor: String?,
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean
)
