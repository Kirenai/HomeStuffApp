package me.kire.re.homestuffapp.domain.port

import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.entity.PurchaseEntity
import me.kire.re.homestuffapp.domain.model.PurchasePriceAndMonth

interface PurchaseRepositoryPort {

    fun getLastTwoPurchases(productId: Long): Flow<List<PurchaseEntity>>

    fun getLast15Prices(productId: Long): Flow<List<PurchasePriceAndMonth>>

    suspend fun insertAllPurchases(purchases: List<PurchaseEntity>)

}