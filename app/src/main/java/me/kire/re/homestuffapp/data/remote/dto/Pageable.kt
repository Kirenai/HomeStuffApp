package me.kire.re.homestuffapp.data.remote.dto

data class Pageable(
    val pageNumber: Long,
    val pageSize: Long,
    val sort: Sort,
    val offset: Long,
    val paged: Boolean,
    val unpaged: Boolean,
)
