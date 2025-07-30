package me.kire.re.homestuffapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.entity.PurchaseEntity

@Dao
interface PurchaseDao {
    @Query(
        """
        SELECT purchaseId, productId, storeName, weightKg, price, timestamp
        FROM purchases 
        WHERE productId = :productId 
        ORDER BY timestamp DESC LIMIT 2
        """
    )
    fun getLastTwoPurchases(productId: Long): Flow<List<PurchaseEntity>>
}