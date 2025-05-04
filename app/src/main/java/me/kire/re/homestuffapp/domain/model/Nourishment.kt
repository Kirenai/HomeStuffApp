package me.kire.re.homestuffapp.domain.model

data class Nourishment(
    val title: String,
    val stock: Int,
    val imageUrl: String,
    val expirationDate: String? = null,
)
