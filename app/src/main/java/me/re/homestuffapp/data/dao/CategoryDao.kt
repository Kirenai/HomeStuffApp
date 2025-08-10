package me.re.homestuffapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.re.homestuffapp.data.entity.CategoryEntity
import me.re.homestuffapp.domain.model.CategoryWithItemCount

@Dao
interface CategoryDao {
    @Query("SELECT categoryId, name from categories ORDER BY name ASC")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("""
        SELECT c.categoryId, c.name, COUNT(p.productId) as itemsCount
        FROM categories c
        LEFT JOIN products p ON c.categoryId = p.categoryId
        GROUP BY c.categoryId
    """)
    fun getCategoriesWithItemsCount(): Flow<List<CategoryWithItemCount>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(category: CategoryEntity)

    @Query("DELETE FROM categories WHERE categoryId = :categoryId")
    suspend fun deleteCategory(categoryId: Long)

    @Update
    suspend fun updateCategory(category: CategoryEntity)
}
