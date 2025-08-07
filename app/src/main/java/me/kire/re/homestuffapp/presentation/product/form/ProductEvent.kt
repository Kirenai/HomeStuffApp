package me.kire.re.homestuffapp.presentation.product.form

sealed class ProductEvent {
    data class OnDeleteProduct(val productId: Long) : ProductEvent()
}