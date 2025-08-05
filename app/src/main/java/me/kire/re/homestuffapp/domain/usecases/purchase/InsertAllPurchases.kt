package me.kire.re.homestuffapp.domain.usecases.purchase

import me.kire.re.homestuffapp.data.dao.PurchaseDao
import me.kire.re.homestuffapp.domain.model.Shopping
import me.kire.re.homestuffapp.util.mapper.toPurchaseEntity
import javax.inject.Inject

class InsertAllPurchases @Inject constructor(
    private val purchaseDao: PurchaseDao
) {
    suspend operator fun invoke(
        shoppingList: List<Shopping>
    ) {
        val purchases = shoppingList
            .map { toPurchaseEntity(it) }
        this.purchaseDao.insertAll(purchases = purchases)
    }
}