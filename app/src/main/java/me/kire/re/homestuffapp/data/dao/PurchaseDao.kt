package me.kire.re.homestuffapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
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

    @Query(
        """
        SELECT price 
        FROM purchases 
        WHERE productId = :productId 
        ORDER BY timestamp DESC LIMIT 15
        """
    )
    fun getLast15Prices(productId: Long): Flow<List<Float>>

    @Insert
    suspend fun insertAll(purchases: List<PurchaseEntity>)
}