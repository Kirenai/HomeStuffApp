package me.re.homestuffapp.data.remote.dto

data class Response(
    val content: List<Content>,
    val pageable: Pageable,
    val last: Boolean,
    val totalPages: Long,
    val totalElements: Long,
    val size: Long,
    val number: Long,
    val sort: Sort,
    val first: Boolean,
    val numberOfElements: Long,
    val empty: Boolean,
)