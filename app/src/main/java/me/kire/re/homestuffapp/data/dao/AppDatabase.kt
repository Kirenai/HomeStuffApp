package me.kire.re.homestuffapp.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import me.kire.re.homestuffapp.data.entity.CategoryEntity

@Database(
    entities = [CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}