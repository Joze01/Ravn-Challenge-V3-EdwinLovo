package com.elovo.data.util

enum class RavnException(val description: String) {
    EXPIRED_TOKEN_EXCEPTION("Expired Token"),
    APOLLO_PARSE_EXCEPTION("There was an error trying to parse GraphQL server response"),
    APOLLO_ERROR("An apollo client error occurred"),
    UNKNOWN_ERROR("An unexpected error occurred")
}
