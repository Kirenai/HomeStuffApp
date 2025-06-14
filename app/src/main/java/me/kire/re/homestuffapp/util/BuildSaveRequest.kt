package me.kire.re.homestuffapp.util

import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentRequest
import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentTypePercentageRequest
import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentTypeRequest
import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentTypeUnitRequest
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormState

fun toCreateNourishmentRequest(
    state: NourishmentFormState
): CreateNourishmentRequest {
    val type: CreateNourishmentTypeRequest = when (state.nourishmentType) {
        "UNIT" -> CreateNourishmentTypeUnitRequest(
            nourishmentType = "UNIT",
            unit = state.unit.toIntOrNull() ?: 0,
        )

        "PERCENTAGE" -> CreateNourishmentTypePercentageRequest(
            nourishmentType = "PERCENTAGE",
            percentage = state.percentage.toDoubleOrNull() ?: 0.0,
        )

        else -> throw IllegalArgumentException("Unknown nourishment type: ${state.nourishmentType}")
    }
    return CreateNourishmentRequest(
        name = state.name,
        description = state.description,
        imageUrl = state.imageUrl,
        type = type,
    )
}