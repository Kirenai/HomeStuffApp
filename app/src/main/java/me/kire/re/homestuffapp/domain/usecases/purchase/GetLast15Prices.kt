package me.kire.re.homestuffapp.domain.usecases.purchase

import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.repository.PurchaseRepository
import javax.inject.Inject

class GetLast15Prices @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {

    operator fun invoke(productId: Long?): Flow<List<Float>> {
        println("Getting last 15 prices for productId: $productId")
        if (productId == null) {
            throw IllegalArgumentException("Product ID cannot be null")
        }
        return this.purchaseRepository.getLast15Prices(productId = productId)
    }

}