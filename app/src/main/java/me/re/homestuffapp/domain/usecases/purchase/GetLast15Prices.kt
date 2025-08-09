package me.re.homestuffapp.domain.usecases.purchase

import kotlinx.coroutines.flow.Flow
import me.re.homestuffapp.data.repository.PurchaseRepository
import me.re.homestuffapp.domain.model.PurchasePriceAndMonth
import javax.inject.Inject

class GetLast15Prices @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {

    operator fun invoke(productId: Long?): Flow<List<PurchasePriceAndMonth>> {
        println("Getting last 15 prices for productId: $productId")
        if (productId == null) {
            throw IllegalArgumentException("Product ID cannot be null")
        }
        return this.purchaseRepository.getLast15Prices(productId = productId)
    }

}