package me.kire.re.homestuffapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.entity.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM items WHERE categoryId = :categoryId")
    fun getItemsByCategory(categoryId: Long): Flow<List<Item>>
}