package me.re.homestuffapp.domain.usecases

import me.re.homestuffapp.domain.model.Product
import me.re.homestuffapp.domain.port.ProductRepositoryPort
import javax.inject.Inject

class UpdateProduct @Inject constructor(
    private val productRepository: ProductRepositoryPort
) {
    suspend operator fun invoke(product: Product) {
        println("Updating product: $product")
        productRepository.updateProduct(product = product)
    }
}