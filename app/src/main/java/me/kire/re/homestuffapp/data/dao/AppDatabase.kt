package me.kire.re.homestuffapp.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import me.kire.re.homestuffapp.data.entity.CategoryEntity
import me.kire.re.homestuffapp.data.entity.Item

@Database(
    entities = [CategoryEntity::class, Item::class],
    version = 3,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun itemDao(): ItemDao
}