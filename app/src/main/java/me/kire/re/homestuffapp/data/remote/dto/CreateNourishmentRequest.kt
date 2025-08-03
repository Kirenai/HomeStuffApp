package me.kire.re.homestuffapp.data.remote.dto

import me.kire.re.homestuffapp.domain.model.enums.UnitType

data class CreateNourishmentRequest(
    val name: String,
    val description: String,
    val imageUrl: String?,
//    val type: CreateNourishmentTypeRequest,
    val categoryId: Long? = null,
    val amountPerUnit: Double? = 1.0,
    val unit: UnitType,
)