package me.kire.re.homestuffapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT id, name, items from categories ORDER BY name ASC")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: CategoryEntity)
}
