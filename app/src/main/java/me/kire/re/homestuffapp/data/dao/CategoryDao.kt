package me.kire.re.homestuffapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.entity.CategoryEntity
import me.kire.re.homestuffapp.domain.model.CategoryWithItemCount

@Dao
interface CategoryDao {
    @Query("SELECT categoryId, name from categories ORDER BY name ASC")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("""
        SELECT c.categoryId, c.name, COUNT(i.itemId) as itemsCount
        FROM categories c
        LEFT JOIN items i ON c.categoryId = i.categoryId
        GROUP BY c.categoryId
    """)
    fun getCategoriesWithItemsCount(): Flow<List<CategoryWithItemCount>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(category: CategoryEntity)
}
