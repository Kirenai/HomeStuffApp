package me.kire.re.homestuffapp.data.repository

import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.dao.PurchaseDao
import me.kire.re.homestuffapp.data.entity.PurchaseEntity
import me.kire.re.homestuffapp.domain.model.PurchasePriceAndMonth
import me.kire.re.homestuffapp.domain.port.PurchaseRepositoryPort
import javax.inject.Inject

class PurchaseRepository @Inject constructor(
    private val purchaseDao: PurchaseDao
) : PurchaseRepositoryPort {
    override fun getLastTwoPurchases(productId: Long): Flow<List<PurchaseEntity>> {
        return this.purchaseDao.getLastTwoPurchases(productId =  productId)
    }

    override fun getLast15Prices(productId: Long): Flow<List<PurchasePriceAndMonth>> {
        return this.purchaseDao.getLast15Prices(productId =  productId)
    }

    override suspend fun insertAllPurchases(purchases: List<PurchaseEntity>) {
        return this.purchaseDao.insertAll(purchases = purchases)
    }
}