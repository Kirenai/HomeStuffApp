package me.kire.re.homestuffapp.util

import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentRequest
import me.kire.re.homestuffapp.domain.model.Product

fun toCreateNourishmentRequest(
    product: Product
): CreateNourishmentRequest {
//    val type: CreateNourishmentTypeRequest = when (state.nourishmentType) {
//        "UNIT" -> CreateNourishmentTypeUnitRequest(
//            nourishmentType = "UNIT",
//            unit = state.unit.toIntOrNull() ?: 0,
//        )
//
//        "PERCENTAGE" -> CreateNourishmentTypePercentageRequest(
//            nourishmentType = "PERCENTAGE",
//            percentage = state.percentage.toDoubleOrNull() ?: 0.0,
//        )
//
//        else -> throw IllegalArgumentException("Unknown nourishment type: ${state.nourishmentType}")
//    }
    return CreateNourishmentRequest(
        name = product.name,
        description = product.description,
        imageUrl = product.imageUrl,
        amountPerUnit = product.amountPerUnit,
        unit = product.unit
    )
}