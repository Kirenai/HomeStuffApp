package me.re.homestuffapp.presentation.product

import me.re.homestuffapp.domain.model.Product

data class ProductState(
    val searchText: String = "",
    val isSorted: Boolean = false,
    val products: List<Product> = emptyList(),
    val inStockProducts: List<Product> = emptyList(),
    val outOfStockProducts: List<Product> = emptyList()
) {
    val filteredProducts: List<Product>
        get() = if (searchText.isBlank()) {
            products
        } else {
            products.filter { nourishment ->
                nourishment.name.contains(searchText, ignoreCase = true)
            }
        }
}
