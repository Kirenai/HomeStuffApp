package me.re.homestuffapp.presentation.product.form

sealed class ProductEvent {
    data class OnDeleteProduct(val productId: Long?) : ProductEvent()
}