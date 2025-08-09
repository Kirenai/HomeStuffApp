package me.re.homestuffapp.data.remote.dto

data class CreateNourishmentTypeUnitRequest(
    val nourishmentType: String,
    val unit: Int
) : CreateNourishmentTypeRequest