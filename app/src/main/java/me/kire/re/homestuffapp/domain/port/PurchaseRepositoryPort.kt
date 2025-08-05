package me.kire.re.homestuffapp.domain.port

import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.entity.PurchaseEntity

interface PurchaseRepositoryPort {

    fun getLastTwoPurchases(productId: Long): Flow<List<PurchaseEntity>>

    fun getLast15Prices(productId: Long): Flow<List<Float>>

    suspend fun insertAllPurchases(purchases: List<PurchaseEntity>)

}