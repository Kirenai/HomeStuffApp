package me.re.homestuffapp.presentation.product.form

import me.re.homestuffapp.domain.model.Product

sealed class ProductEvent {
    data class OnDeleteProduct(val productId: Long?) : ProductEvent()
    data class OnUpdateProduct(val product: Product) : ProductEvent()
}