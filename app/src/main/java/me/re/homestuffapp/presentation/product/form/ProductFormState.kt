package me.re.homestuffapp.presentation.product.form

import me.re.homestuffapp.domain.model.enums.UnitType

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
