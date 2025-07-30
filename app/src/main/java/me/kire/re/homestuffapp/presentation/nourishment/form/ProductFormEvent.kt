package me.kire.re.homestuffapp.presentation.nourishment.form

sealed class ProductFormEvent {
    data object SaveProduct : ProductFormEvent()
    data class NameChanged(val name: String) : ProductFormEvent()
    data class DescriptionChanged(val description: String) : ProductFormEvent()
    data class ProductTypeChanged(
        val nourishmentType: String,
        val value: String
    ) : ProductFormEvent()
}
