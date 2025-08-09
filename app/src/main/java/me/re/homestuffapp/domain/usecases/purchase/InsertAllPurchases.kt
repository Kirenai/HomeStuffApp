package me.re.homestuffapp.domain.usecases.purchase

import me.re.homestuffapp.domain.model.Shopping
import me.re.homestuffapp.domain.port.PurchaseRepositoryPort
import me.re.homestuffapp.util.mapper.toPurchaseEntity
import javax.inject.Inject

class InsertAllPurchases @Inject constructor(
    private val purchaseRepositoryPort: PurchaseRepositoryPort
) {
    suspend operator fun invoke(
        shoppingList: List<Shopping>
    ) {
        val purchases = shoppingList
            .map { toPurchaseEntity(it) }
        this.purchaseRepositoryPort.insertAllPurchases(purchases = purchases)
    }
}