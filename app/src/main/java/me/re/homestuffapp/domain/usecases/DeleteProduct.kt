package me.re.homestuffapp.domain.usecases

import me.re.homestuffapp.domain.port.ProductRepositoryPort
import javax.inject.Inject

class DeleteProduct @Inject constructor(
    private val productRepository: ProductRepositoryPort
) {
    suspend operator fun invoke(productId: Long?) {
        if (productId == null) {
            throw IllegalArgumentException("Product ID cannot be null")
        }
        productRepository.deleteProduct(productId = productId)
    }
}