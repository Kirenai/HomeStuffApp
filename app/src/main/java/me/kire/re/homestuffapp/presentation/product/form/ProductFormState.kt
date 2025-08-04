package me.kire.re.homestuffapp.presentation.product.form

import me.kire.re.homestuffapp.domain.model.enums.UnitType

data class ProductFormState(
    val name: String = "",
    val description: String = "",
    val imageUrl: String? = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
    val amountPerUnit: String? = null,
    val unit: UnitType = UnitType.UNIT
)
