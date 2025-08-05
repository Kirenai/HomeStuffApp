package me.kire.re.homestuffapp.domain.usecases.purchase

import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.entity.PurchaseEntity
import me.kire.re.homestuffapp.data.repository.PurchaseRepository
import javax.inject.Inject

class GetLastTwoPurchases @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {

    operator fun invoke(productId: Long?): Flow<List<PurchaseEntity>> {
        println("Getting last two purchases for productId: $productId")
        if (productId == null) {
            throw IllegalArgumentException("Product ID cannot be null")
        }
        return purchaseRepository.getLastTwoPurchases(productId = productId)
    }

}