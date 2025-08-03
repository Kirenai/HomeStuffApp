package me.kire.re.homestuffapp.domain.usecases

import me.kire.re.homestuffapp.domain.model.Product
import me.kire.re.homestuffapp.domain.port.ProductRepositoryPort
import javax.inject.Inject

class SaveProduct @Inject constructor(
    private val repository: ProductRepositoryPort
) {
    suspend operator fun invoke(
        product: Product
    ) {
        println("Saving product: $product")
        repository.saveProductRoom(product)
    }
}