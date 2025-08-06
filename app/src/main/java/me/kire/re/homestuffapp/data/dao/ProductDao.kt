package me.kire.re.homestuffapp.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import me.kire.re.homestuffapp.data.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("""
        SELECT productId, name, description, imageUrl, categoryId, isAvailable, amountPerUnit, unit 
        FROM products 
        WHERE categoryId = :categoryId
    """)
    fun getProductsByCategory(categoryId: Long): PagingSource<Int, ProductEntity>

    @Query("""
        SELECT productId, name, description, imageUrl, categoryId, isAvailable, amountPerUnit, unit 
        FROM products 
        WHERE productId IN (:ids)
    """)
    suspend fun loadProductsFromIds(ids: List<Long>): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(productEntity: ProductEntity)

    @Query("""
        UPDATE products 
        SET amountPerUnit = :newAmountPerUnit 
        WHERE productId = :productId
    """)
    suspend fun updatePrice(productId: Long, newAmountPerUnit: Float)

    @Update
    suspend fun updateProducts(products: List<ProductEntity>)

}