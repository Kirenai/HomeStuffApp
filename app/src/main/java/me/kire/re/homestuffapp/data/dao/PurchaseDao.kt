package me.kire.re.homestuffapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.entity.PurchaseEntity
import me.kire.re.homestuffapp.domain.model.PurchasePriceAndMonth

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
        SELECT price, strftime('%m', timestamp / 1000, 'unixepoch') AS month
        FROM purchases 
        WHERE productId = :productId 
        ORDER BY timestamp ASC
        LIMIT 15
        """
    )
    fun getLast15Prices(productId: Long): Flow<List<PurchasePriceAndMonth>>

    @Insert
    suspend fun insertAll(purchases: List<PurchaseEntity>)
}