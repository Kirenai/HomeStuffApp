package me.kire.re.homestuffapp.data.remote.dto

data class CreateNourishmentTypePercentageRequest(
    val nourishmentType: String,
    val percentage: Double
) : CreateNourishmentTypeRequest