package me.kire.re.homestuffapp.data.remote.dto

data class CreateNourishmentRequest(
    val description: String,
    val imageUrl: String?,
    val name: String,
    val type: CreateNourishmentTypeRequest
)