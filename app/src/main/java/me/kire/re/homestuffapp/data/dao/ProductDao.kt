package me.kire.re.homestuffapp.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.kire.re.homestuffapp.data.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE categoryId = :categoryId")
    fun getItemsByCategory(categoryId: Long): PagingSource<Int, ProductEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(productEntity: ProductEntity)
}