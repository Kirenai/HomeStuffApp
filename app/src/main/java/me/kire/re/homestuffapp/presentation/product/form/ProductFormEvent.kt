package me.kire.re.homestuffapp.presentation.product.form

import me.kire.re.homestuffapp.domain.model.enums.UnitType

sealed class ProductFormEvent {
    data class SaveProduct(val categoryId: Long) : ProductFormEvent()

    data class NameChanged(val name: String) : ProductFormEvent()

    data class DescriptionChanged(val description: String) : ProductFormEvent()

    data class AmountOnChanged(val amount: String) : ProductFormEvent()

    data class UnitOnChanged(val unitType: UnitType) : ProductFormEvent()
}
