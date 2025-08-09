package me.re.homestuffapp.domain.usecases

import me.re.homestuffapp.domain.port.ProductRepositoryPort
import javax.inject.Inject

class DeleteProduct @Inject constructor(
    private val productRepository: ProductRepositoryPort
) {
    suspend operator fun invoke(productId: Long) {
        productRepository.deleteProduct(productId = productId)
    }
}